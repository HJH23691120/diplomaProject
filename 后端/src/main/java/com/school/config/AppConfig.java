package com.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author mjz
 * @Date 2022/4/7 15:20
 * @Version 1.0
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowCredentials(false)
                .allowedMethods("POST","GET","DELETE","PUT","OPTIONS")
                .allowedOrigins("*");
    }
}
