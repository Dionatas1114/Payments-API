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
public class ServicesDto {

    public UUID id;
    public String itemName;
    public String itemCategory;
    public double totalPrice;
    public double discountPrice;
    public String internalCode;
    public String itemDescription;

}
