package com.api.payments.config;

import com.api.payments.interceptor.TokenInterceptor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Getter
@Configuration
@AllArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    private TokenInterceptor tokenInterceptor;
    private Properties properties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns(properties.getSecurePaths()) // add list of protected routes
                .excludePathPatterns(properties.getPublicPaths()); // add list of public routes (ex. login page)
    }
}