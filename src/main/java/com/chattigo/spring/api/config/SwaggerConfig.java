package com.chattigo.spring.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * Se genera la documentación del API con swagger se puede acceder a ella en el siguiente @link (http://localhost:8080/api/v1/swagger-ui.html).
   * @return Docket
   */
  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.chattigo.spring.api.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(
        "Gateway Service API",
        "Gateway Service API Description",
        "1.0",
        "",
        new Contact("Edysnay Ayala Ballester", "", "legolashellman@gmail.com"),
        "LICENSE",
        "LICENSE URL",
        Collections.emptyList()
    );
  }
}
