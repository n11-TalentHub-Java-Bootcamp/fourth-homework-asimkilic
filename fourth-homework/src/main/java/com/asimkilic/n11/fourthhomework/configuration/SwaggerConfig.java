package com.asimkilic.n11.fourthhomework.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("asimkilic-hw4")
                .pathsToMatch("/asimkilic/**")
                .build();
    }
    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String description, @Value("${application-version}") String version) {
        return new OpenAPI()
                .info(new Info()
                        .title("DEBT API")
                        .version(version)
                        .description(description)
                        .license(new License().name("DEBT API licence")));
    }






}
