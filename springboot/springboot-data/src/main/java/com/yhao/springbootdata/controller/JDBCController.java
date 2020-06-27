package com.yhao.springbootdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// @Controller:返回html,jsp页面
// @RestController:只返回 return 后面的内容，无法返回jsp.html页面
// @ResponseController: 返回自定义类型的页面，如json,xml
@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 没有实体类，查询数据库，可以使用Map来接受数据
    @GetMapping("/subjects")
    public List<Map<String, Object>> subjects() {
        String sql = "select * from tb_subject";
        return jdbcTemplate.queryForList(sql);
    }

}
