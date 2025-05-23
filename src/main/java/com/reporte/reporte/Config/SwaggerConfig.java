package com.reporte.reporte.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI().info(
            new Info()
                .title("Curso API")
                .version("1.0")
                .description("API para gestionar cursos")
        );
}

}