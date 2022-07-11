package com.api.payments.controller;

import com.api.payments.config.WhatsAppConfig;
import com.api.payments.dto.WhatsAppLanguageDto;
import com.api.payments.dto.WhatsAppMessagesDto;
import com.api.payments.dto.WhatsAppTemplateDto;
import com.api.payments.services.PaymentService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

import static com.api.payments.messages.UserMessages.badRequest;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class WhatsAppMessagesController {

        WhatsAppConfig whatsAppConfig;
        PaymentService paymentService;

        @ApiOperation(
                value = "Returns WhatsApp Messages",
                notes = "This Request Send WhatsApp Messages according to the current date",
                tags = {"WhatsApp Messages"})
        @ApiResponses(
                value = {
                        @ApiResponse(
                                code = 200,
                                message = "Send WhatsApp Messages",
                                response = WhatsAppMessagesDto.class),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized Access"),
                        @ApiResponse(code = 404, message = "Not Found")
                })
        @GetMapping(path = {"/whatsapp"})
        public ResponseEntity sendWhatsAppMessages() throws Exception {

                ResponseEntity result;

//                LocalDate date = LocalDate.now();
//                List<PaymentsDto> paymentsByExpirationDate =
//                        paymentService.findPaymentsByExpirationDate(date);
//
//                for(PaymentsDto payments : paymentsByExpirationDate) {
//                        payments.debtorFullName
//                }

                try {
                        var whatsAppLanguageDto = new WhatsAppLanguageDto();
                        whatsAppLanguageDto.setCode("pt_BR");//en_US

                        var whatsAppTemplateDto = new WhatsAppTemplateDto();
                        whatsAppTemplateDto.setName("new_message");
                        whatsAppTemplateDto.setLanguage(whatsAppLanguageDto);

                        var whatsAppMessagesDto = new WhatsAppMessagesDto();
                        whatsAppMessagesDto.setMessaging_product("whatsapp");
                        whatsAppMessagesDto.setPreview_url(false);
                        whatsAppMessagesDto.setRecipient_type("individual");
                        whatsAppMessagesDto.setTo(whatsAppConfig.getWHATSAPP_RECIPIENT_PHONE_NUMBER());
                        whatsAppMessagesDto.setType("template");
                        whatsAppMessagesDto.setTemplate(whatsAppTemplateDto);

                        String json = new Gson().toJson(whatsAppMessagesDto);

                        String route = "messages";
                        String method = "POST";

                        HttpResponse<String> response = whatsAppConfig.CRUD(route, method, json);

                        result = new ResponseEntity<>(response.body(),
                                HttpStatus.valueOf(response.statusCode()));

                } catch (Exception e) {
                        result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
                }
                return result;
        }
}
