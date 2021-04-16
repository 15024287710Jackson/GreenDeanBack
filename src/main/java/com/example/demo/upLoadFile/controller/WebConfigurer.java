package com.example.demo.upLoadFile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

//    @Value("${web.upload-path}")
    private String mImagesPath="/root/GreenDeanUpLoad/video/";
    //private String mImagesPath="E:/GreenDeanUpLoad/video/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //重写重定向位置
        registry.addResourceHandler("/video/**").addResourceLocations("file:///" + mImagesPath);
    }
}