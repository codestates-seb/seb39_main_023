package com.team23.mainPr.Config.WebConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * feat:cors 모두 열어 놓기
 * </pre>
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**>")
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOriginPatterns("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/swagger-ui.html");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/");
    }
}
