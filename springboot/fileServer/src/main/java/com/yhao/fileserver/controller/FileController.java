package com.yhao.fileserver.controller;

import com.yhao.fileserver.entity.User;
import com.yhao.fileserver.entity.UserFile;
import com.yhao.fileserver.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    UserFileService userFileService;

    // 展示所有的文件
    @GetMapping("allFile")
    public String queryAllFile(HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        List<UserFile> files = userFileService.queryByUserId(user.getId());
        System.out.println(files);
        // 存入作用域中
        model.addAttribute("files", files);
        return "/file/list";
    }
}
