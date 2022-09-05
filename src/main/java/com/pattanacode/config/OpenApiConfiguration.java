package com.pattanacode.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        description = "Insert JWT Token obtained from user. Authorization URL: https://pattanacode.herokuapp.com/auth/login",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Customer Privilege Program Royalty Project API Services")
                        .description("This document contains the documentation for using the dental clinic API. Base url: https://ccps.kiatnakinbank.com/")
                        .contact(new Contact()
                                .name("Pattana Kaewsai")
                                .email("patismyname@gmail.com")
                                .url("https://pattanacode.heraku.com/")
                        )
                        .version("1.0.0")
                );
    }
}
