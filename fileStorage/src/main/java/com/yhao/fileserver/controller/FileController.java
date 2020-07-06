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
import java.io.*;
import java.net.URLEncoder;
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
    @GetMapping("/index")
    public String fileIndex() {
        return "file/list";
    }

    @GetMapping("all")
    @ResponseBody
    public List<UserFile> queryAllFile(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<UserFile> files = userFileService.queryByUserId(user.getId());
        return files;
    }

    // 上传文件到数据库
    @PostMapping("upload")
    @ResponseBody
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) {
        System.out.println("uploadFile trigger");

        Map<String, String> res = new HashMap<>();
        try {
            // 获取上传用户的Id
            User user = (User) session.getAttribute("user");
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
            File dateDirPath = new File(uploadPath + File.separator + dateFormat);
            if (!dateDirPath.exists()) {
                dateDirPath.mkdirs();
            }
            System.out.println(dateDirPath);
            // 处理文件上传
            file.transferTo(new File(dateDirPath, fileName));
            // 将文件信息存入数据库中
            UserFile userFile = new UserFile();
            userFile.setFileName(fileName)
                    .setExt('.' + extension)
                    .setPath(Paths.get(localContainer, dateFormat, fileName).toString())
                    .setSize(size)
                    .setType(type)
                    .setUserId(user.getId());

            userFileService.save(userFile);

            res.put("code", "0");
            res.put("msg", "上传成功");
            res.put("url", "/fileServer/file/index");
        } catch (IOException e) {
            e.printStackTrace();
            res.put("code", "-1");
            res.put("msg", "上传失败");
            res.put("url", "/fileServer/file/index");
        }

        return res;
    }

    // 文件下载
    @GetMapping("download/{id}")
    public void download(@PathVariable("id") Integer id, HttpServletResponse response){
        String openStyle = "attachment";
        System.out.println("trigger download");
        try{
            getFile(openStyle,id,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 文件预览
    @GetMapping("preview/{id}")
    public void preview(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        String openStyle = "inline";
        getFile(openStyle,id,response);
    }

    public void getFile(String openStyle, Integer id, HttpServletResponse response) throws IOException {
        UserFile file = userFileService.queryByUserFileId(id);
        // 获取文件信息
        final String realPath = ResourceUtils.getURL("classpath").getPath() + file.getPath();
        // 获取文件输入流
        FileInputStream is = new FileInputStream(new File(realPath));
        // 附件下载
        response.setHeader("content-disposition", openStyle+";filename=" + URLEncoder.encode(file.getFileName(), "UTF-8"));
        // 获取响应response输出流
        ServletOutputStream os = response.getOutputStream();
        // 文件拷贝
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
        // 更新下载次数
        if(openStyle.equals("attachment")){
            file.setDownloadCounts(file.getDownloadCounts() + 1);
            userFileService.update(file);
        }
    }

    // 文件删除
    @GetMapping("delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id) {
        Map<String,Object> map = new HashMap<>();
        try{
            UserFile fileInfo = userFileService.queryByUserFileId(id);
            final String realPath = ResourceUtils.getURL("classpath").getPath() + fileInfo.getPath();
            File file = new File(realPath);
            if(file.exists()){
                file.delete();  //立即删除
            }
            userFileService.delete(id);
            map.put("code",0);
            map.put("msg","删除成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            map.put("code",-1);
            map.put("msg","删除成功！");
        }
        return map;
//        return "redirect:/file/all";
    }
}
