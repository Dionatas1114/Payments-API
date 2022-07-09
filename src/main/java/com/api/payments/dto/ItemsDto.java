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
public class ItemsDto {

    public UUID id;
    public String itemName;
    public String itemType;  //product or service
    public String productBrand;
    public String category;
    public String manufacturer; //fabricante
    public String captionPacking; //KG, L
    public double totalPrice;
    public double unitaryPrice;
    public double discountPrice; //if wholesale (atacado)
    public String barCode;
    public String internalCode;
    public String description;

}
