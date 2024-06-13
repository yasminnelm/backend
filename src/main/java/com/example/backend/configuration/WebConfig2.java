package com.example.backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig2 implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")  // Emplacement des ressources statiques
                .addResourceLocations("classpath:/public/")  // Emplacement des ressources publiques
                .addResourceLocations("classpath:/META-INF/resources/")  // Emplacement des ressources de méta-informations
                .addResourceLocations("classpath:/resources/") // Emplacement des ressources
                .addResourceLocations("classpath:/webjars/"); // Emplacement des ressources de webjars
    }
}
