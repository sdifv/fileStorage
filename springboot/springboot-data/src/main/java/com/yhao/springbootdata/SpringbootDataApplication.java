package com.yhao.springbootdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 手动指定扫描 mapper 的包
//@MapperScan("com.yhao.springbootdata.mapper")
public class SpringbootDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDataApplication.class, args);
    }

}
