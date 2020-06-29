package com.yhao.fileserver.controller;

import com.yhao.fileserver.entity.User;
import com.yhao.fileserver.entity.UserFile;
import com.yhao.fileserver.service.UserFileService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    UserFileService userFileService;

    // 展示所有的文件
    @GetMapping("all")
    public String queryAllFile(HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        List<UserFile> files = userFileService.queryByUserId(user.getId());
        // System.out.println(files);
        // 存入作用域中
        model.addAttribute("files", files);
        return "/file/list";
    }

    // 上传文件到数据库
    @PostMapping("upload")
    @ResponseBody
    public Map<String,String> uploadFile(@RequestParam("file")MultipartFile file, HttpSession session){
        System.out.println("uploadFile trigger");

        Map<String,String> res = new HashMap<>();
        try{
            // 获取上传用户的Id
            User user = (User)session.getAttribute("user");
            // 获取文件的原始名称
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            // 获取文件的大小
            long size = file.getSize();
            // 获取文件的类型
            String type = file.getContentType();
            // 根据日期动态的生成目录
            String localContainer = "/fileContainer";
            String uploadPath = ResourceUtils.getURL("classpath").getPath() + localContainer;
            String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File dateDirPath = new File(uploadPath+File.separator+dateFormat);
            if(!dateDirPath.exists()){
                dateDirPath.mkdirs();
            }
            System.out.println(dateDirPath);
            // 处理文件上传
            file.transferTo(new File(dateDirPath, fileName));
            // 将文件信息存入数据库中
            UserFile userFile = new UserFile();
            userFile.setFileName(fileName)
                    .setExt('.'+extension)
                    .setPath(Paths.get(localContainer,dateFormat,fileName).toString())
                    .setSize(size)
                    .setType(type)
                    .setUserId(user.getId());

            userFileService.save(userFile);

            res.put("code","0");
            res.put("msg","上传成功");
            res.put("url","/fileServer/file/all");
        }catch (IOException e){
            e.printStackTrace();
            res.put("code","-1");
            res.put("msg","上传失败");
            res.put("url","/fileServer/file/all");
        }

        return res;
    }

    // 文件下载
    @GetMapping("download/{id}")
    public String download(@PathVariable("id")Integer id, HttpServletResponse response) throws IOException {
        UserFile file = userFileService.queryByUserFileId(id);
        // 获取文件信息
        final String realPath = ResourceUtils.getURL("classpath").getPath() + file.getPath();
        // 获取文件输入流
        FileInputStream is = new FileInputStream(new File(realPath));
        // 附件下载

        // 获取相应输出流
        ServletOutputStream os = response.getOutputStream();
        // 文件拷贝
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
        // 更新下载次数
        file.setDownloadCounts(file.getDownloadCounts()+1);
        userFileService.update(file);

        return "redirect:all";
    }
}
