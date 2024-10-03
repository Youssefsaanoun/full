package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@SuppressWarnings("null")
public class webConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(  CorsRegistry registry) {
        // Allow the frontend (e.g., React) on localhost:5173 to access the backend APIs
        registry.addMapping("/Product/**")  // Mapping for your Product API endpoint
                .allowedOrigins("http://localhost:5173")  // Specify the frontend's URL
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specify allowed HTTP methods
                .allowedHeaders("*")
                .allowCredentials(true);
                
                
                // Allow all headers

    }
}
