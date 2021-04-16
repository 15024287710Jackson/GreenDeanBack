package com.example.demo.upLoadFile.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
//        String path = System.getProperty("user.dir")+"\\GreenDeanBack\\src\\main\\resources\\static\\images\\";
        //String path = "E:\\GreenDeanUpLoad\\picture\\";
        String path = "/root/GreenDeanBack/picture";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/images/**").
                    addResourceLocations("file:" + path);
        }

    }
}