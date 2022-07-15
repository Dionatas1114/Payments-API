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
public class ProductsDto {

    public UUID id;
    public String itemName;
    public String itemType;
    public String productBrand;
    public String itemCategory;
    public String manufacturer;
    public String captionPacking;
    public double totalPrice;
    public double unitaryPrice;
    public double discountPrice;
    public String barCode;
    public String internalCode;
    public String itemDescription;

}
