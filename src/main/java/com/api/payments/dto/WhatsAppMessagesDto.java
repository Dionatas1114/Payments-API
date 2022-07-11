package com.api.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WhatsAppMessagesDto {

    public UUID id;
    public String messaging_product;
    public Boolean preview_url;
    public String recipient_type;
    public String to;
    public String type;
    public WhatsAppTemplateDto template;

}
