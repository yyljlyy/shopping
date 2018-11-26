package com.luckin.innovation.group.front.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Configuration
public class SessionConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**").excludePathPatterns("/layui/**");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128000KB");
        factory.setMaxRequestSize("128000KB");
        return factory.createMultipartConfig();
    }



}
