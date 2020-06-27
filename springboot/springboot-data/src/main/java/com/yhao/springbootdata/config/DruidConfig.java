package com.yhao.springbootdata.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    // 后台监控,springboot内置了servlet容器，所以没有web.xml,替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean StatViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 后台需要有人登录，账号密码配置
        HashMap<String,String> initParameters = new HashMap<>();
        // 配置参数需要写在一个Map中来传参,key是定义好的
        initParameters.put("loginUserName","root");
        initParameters.put("loginPassword","123456");
        initParameters.put("allow","");

        bean.setInitParameters(initParameters);
        return bean;
    }
}
