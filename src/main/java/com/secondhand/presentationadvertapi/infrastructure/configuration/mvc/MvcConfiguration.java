package com.secondhand.presentationadvertapi.infrastructure.configuration.mvc;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Value("${spring.application.name:advert_unknown}")
    private String applicationName;

    public MvcConfiguration() {
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorrelationIdInterceptor());
        registry.addInterceptor(new AgentNameInterceptor(this.applicationName));
        registry.addInterceptor(new UserInfoInterceptor());
        registry.addInterceptor(new RequestPathInterceptor());
        registry.addInterceptor(new CorsInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders()
                .allowedOrigins("**")
                .allowedHeaders("*")
                .exposedHeaders("Location", "Access-Control-Allow-Origin");
    }
}
