package com.asimkilic.n11.fourthhomework.configuration;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
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
/*
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("n11 TalentHub Java Bootcamp")
                .description("Fourth Week Homework")
                .contact(new Contact("Abdullah Asım KILIÇ", "https://github.com/asimkilic", "a.asim.kilic@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/license/LICENSE-2.0.html")
                .version("1.0.1")
                .build();
    }*/





}
