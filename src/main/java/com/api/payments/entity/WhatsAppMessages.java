package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WHATSAPP_MESSAGES")
@EqualsAndHashCode(callSuper = true)
public class WhatsAppMessages extends BaseEntity{

    @ApiModelProperty(notes = "Texto da mensagem")
    @Column(nullable = false, length = 50)
    public String textMessage;
}
