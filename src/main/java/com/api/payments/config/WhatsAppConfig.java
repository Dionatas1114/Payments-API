package com.api.payments.config;

import com.api.payments.config.utils.CheckEnvVars;
import com.api.payments.validations.validators.WhatsAppValidator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Getter
@Configuration
public class WhatsAppConfig {

    @Value("${WHATSAPP.VERSION}")
    private String WHATSAPP_VERSION;

    @Value("${WHATSAPP.PHONE_NUMBER_ID}")
    private String WHATSAPP_PHONE_NUMBER_ID;

    @Value("${WHATSAPP.RECIPIENT_PHONE_NUMBER}")
    private String WHATSAPP_RECIPIENT_PHONE_NUMBER;

    @Value("${WHATSAPP.URL}")
    private String WHATSAPP_URL;

    @Value("${WHATSAPP.PERMANENT_TOKEN}")
    private String WHATSAPP_PERMANENT_TOKEN;

    @Value("${WHATSAPP.AMOUNT_SECONDS}")
    private String WHATSAPP_AMOUNT_SECONDS;

    public WhatsAppValidator whatsAppValidator;

    @Bean
    public void checkWhatsAppVars(){

        var envVarType = "WHATSAPP ENV_VARS";

        String[] envVarValue = {
                getWHATSAPP_VERSION(),
                getWHATSAPP_PHONE_NUMBER_ID(),
                getWHATSAPP_RECIPIENT_PHONE_NUMBER(),
                getWHATSAPP_URL(),
                getWHATSAPP_PERMANENT_TOKEN(),
                getWHATSAPP_AMOUNT_SECONDS()
        };

        String[] envVarName = {
                "WHATSAPP_VERSION",
                "WHATSAPP_PHONE_NUMBER_ID",
                "WHATSAPP_RECIPIENT_PHONE_NUMBER",
                "WHATSAPP_URL",
                "WHATSAPP_PERMANENT_TOKEN",
                "WHATSAPP_AMOUNT_SECONDS"
        };

        CheckEnvVars.checkEnvVarsIsNotNull(envVarType, envVarValue, envVarName);
    }

    public HttpResponse<String> CRUD(
            String route,
            String method,
            String body
    ) throws IOException, InterruptedException {

        HttpResponse<String> response;

        WhatsAppValidator.whatsAppValidate(route, method, body);

        var url = getWHATSAPP_URL()
                + getWHATSAPP_VERSION() + "/"
                + getWHATSAPP_PHONE_NUMBER_ID() + "/" + route;

        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", getWHATSAPP_PERMANENT_TOKEN())
                .header("Content-Type", "application/json")
                .timeout(Duration.of(Long.parseLong(getWHATSAPP_AMOUNT_SECONDS()), SECONDS))
                .method(method, HttpRequest.BodyPublishers.ofString(body))
                .build();

        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }
}
