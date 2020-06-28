package com.yhao.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @ Author     ：Yan
 * @ Date       ：Created in 19:33 2020/4/7
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求
        String language=request.getParameter("l");
        Locale locale=Locale.getDefault();//如果没有就使用默认的
        //如果请求的链接携带了国际化的参数
        if(!StringUtils.isEmpty(language)){
            //zh_CN
            String[] split=language.split("_");
            //国家，地区
            locale=new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
