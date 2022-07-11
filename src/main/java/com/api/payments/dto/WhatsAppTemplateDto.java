package com.api.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WhatsAppTemplateDto {

    public String name;
    public WhatsAppLanguageDto language;
//    public ComponentsDto components;

}
