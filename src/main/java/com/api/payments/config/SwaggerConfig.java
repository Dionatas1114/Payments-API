package com.api.payments.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class SwaggerConfig {

    @Value("${PROJ.TITLE}")
    private String TITLE;

    @Value("${PROJ.VERSION}")
    private String VERSION;

    @Value("${PROJ.DESCRIPTION}")
    private String DESCRIPTION;

    @Value("${OWNER.NAME}")
    private String NAME;

    @Value("${OWNER.URL}")
    private String URL;

    @Value("${OWNER.EMAIL}")
    private String EMAIL;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.infoApi().build());
    }

    private ApiInfoBuilder infoApi() {
        return new ApiInfoBuilder()
            .title(TITLE)
            .version(VERSION)
            .description(DESCRIPTION)
            .contact(new Contact(NAME,URL,EMAIL));
    }
}
