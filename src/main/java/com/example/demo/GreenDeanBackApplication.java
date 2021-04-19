package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
//@MapperScan(basePackages = "org.sang.mybatis.mapper")
public class GreenDeanBackApplication {

	public static void main(String[] args)  {
		SpringApplication.run(GreenDeanBackApplication.class, args);
	}

	/**
	 * 文件上传配置
	 *
	 * @return
//	 */
//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
///*
//     springBoot 1.0+ 推荐这种写法
//      MultipartConfigFactory factory = new MultipartConfigFactory();
//      //单个文件最大
//      factory.setMaxFileSize("20480KB"); //KB,MB
//      factory.setMaxFileSize("20480KB");
//      /// 设置总上传数据总大小
//      factory.setMaxRequestSize("102400KB");
//      return factory.createMultipartConfig();
//
//*/
//		// springBoot 2.0+ 推荐这种写法，
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		//文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
//		factory.setMaxFileSize(DataSize.of(20, DataUnit.GIGABYTES));
//		/// 设置总上传数据总大小10M
//		factory.setMaxRequestSize(DataSize.of(20, DataUnit.GIGABYTES));
//		return factory.createMultipartConfig();
//	}


}
