package com.yhao.fileserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileserverApplication.class, args);
    }

}
