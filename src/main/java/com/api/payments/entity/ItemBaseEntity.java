package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class ItemBaseEntity extends BaseEntity {

    @ApiModelProperty(notes = "Nome do item")
    @Column(nullable = false, length = 50)
    public String itemName;

    @ApiModelProperty(notes = "Tipo do item: product or service")
    @Column(nullable = false, length = 50)
    public String itemType;

    @ApiModelProperty(notes = "Categoria do item: Other (def)")
    public String itemCategory = "Other";

    @ApiModelProperty(notes = "Preço total do item")
    @Column(nullable = false)
    public double totalPrice;

    @ApiModelProperty(notes = "Valor do desconto do item caso seja atacado (wholesale): 0.00 (def)")
    public double discountPrice = 0.00;

    @ApiModelProperty(notes = "Código interno do item")
    @Column(nullable = false)
    public String internalCode;

    @ApiModelProperty(notes = "Descrição do item")
    @Column(length = 100)
    public String itemDescription;

}
