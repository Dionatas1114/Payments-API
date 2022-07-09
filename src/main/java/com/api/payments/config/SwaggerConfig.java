package com.api.payments.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Getter
@Configuration
public class SwaggerConfig {

    @Value("${PROJ.TITLE}")
    private String PROJ_TITLE;

    @Value("${PROJ.VERSION}")
    private String PROJ_VERSION;

    @Value("${PROJ.DESCRIPTION}")
    private String PROJ_DESCRIPTION;

    @Value("${OWNER.NAME}")
    private String OWNER_NAME;

    @Value("${OWNER.URL}")
    private String OWNER_URL;

    @Value("${OWNER.EMAIL}")
    private String OWNER_EMAIL;

    @Bean
    public Docket api() {
        checkVars();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(infoApi().build());
    }

    private ApiInfoBuilder infoApi() {
        return new ApiInfoBuilder()
            .title(PROJ_TITLE)
            .version(PROJ_VERSION)
            .description(PROJ_DESCRIPTION)
            .contact(new Contact(
                    OWNER_NAME,OWNER_URL,OWNER_EMAIL));
    }

    private void checkVars(){

        String envVarType = "SWAGGER ENV_VARS";

        String[] envVarValue = {
                getPROJ_TITLE(),
                getPROJ_VERSION(),
                getPROJ_DESCRIPTION(),
                getOWNER_NAME(),
                getOWNER_URL(),
                getOWNER_EMAIL()
        };

        String[] envVarName = {
                "PROJ_TITLE",
                "PROJ_VERSION",
                "PROJ_DESCRIPTION",
                "OWNER_NAME",
                "OWNER_URL",
                "OWNER_EMAIL"
        };

        CheckEnvVars.checkEnvVarsIsNotNull(
                envVarType, envVarValue, envVarName);
    }
}
