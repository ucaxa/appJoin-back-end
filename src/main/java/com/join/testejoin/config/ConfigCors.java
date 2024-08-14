package com.join.testejoin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigCors {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Aplica CORS a todos os endpoints
                        .allowedOrigins("*")  // Permite todas as origens
                        .allowedMethods("*")  // Permite todos os métodos HTTP (GET, POST, etc.)
                        .allowedHeaders("*")  // Permite todos os cabeçalhos
                        .allowCredentials(false); // Desativa o envio de credenciais (opcional)
            }
        };
    }
}
