package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class ItemBaseEntity extends BaseEntity {

    @ApiModelProperty(notes = "Nome do item")
    @Column(nullable = false, length = 50)
    public String itemName;

    @ApiModelProperty(notes = "Categoria do item, default é 'Other', examplo = T-Shirt")
    public String itemCategory = "Other";

    @ApiModelProperty(notes = "Preço total do item")
    @Column(nullable = false)
    public double totalPrice;

    @ApiModelProperty(notes = "Valor do desconto do item caso seja atacado (wholesale), default é '0.00', examplo = 1.00")
    public double discountPrice = 0.00;

    @ApiModelProperty(notes = "Código interno do item")
    @Column(nullable = false)
    public String internalCode;

    @ApiModelProperty(notes = "Descrição do item, default é 'None', examplo = Produto XYZ")
    @Column(length = 100)
    public String itemDescription = "None";

}
