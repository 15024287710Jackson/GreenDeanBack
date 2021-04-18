package com.example.demo.tomcat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class TomcatConfig {

//    @Value("${spring.server.MaxFileSize}")
    private String MaxFileSize="300MB";
//    @Value("${spring.server.MaxRequestSize}")
    private String MaxRequestSize="500MB";

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.parse(MaxFileSize)); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse(MaxRequestSize));
        return factory.createMultipartConfig();
    }
}