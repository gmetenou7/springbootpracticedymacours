package com.dyma.springpracticecours.security;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {@Server(url = "/", description = "Default Server URL")},
        info = @Info(title = "DOOKE API TEST PROJET", version = "1.0")
)
@Configuration
public class SwaggerConfiguration {
}
