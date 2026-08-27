package com.corecode.helpdesk.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI clinicaOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Sistema de Chamados API")
                .version("1.0.0")
                .description("API didatica para testar sistema de chamados para TI."));
    }
}
