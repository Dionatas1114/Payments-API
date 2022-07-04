package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEMS")
@EqualsAndHashCode(callSuper = true)
public class Items extends BaseEntity {

    @ApiModelProperty(notes = "Nome do item")
    @Column(nullable = false, length = 50)
    public String itemName;

    @ApiModelProperty(notes = "Tipo do item: product or service")
    @Column(nullable = false, length = 50)
    public String itemType;

    @ApiModelProperty(notes = "Marca do item")
    public String productBrand;

    @ApiModelProperty(notes = "Categoria do item: Other (def)")
    public String itemCategory = "Other";

    @ApiModelProperty(notes = "Fabricante do item: Uninformed (def)")
    public String manufacturer = "Uninformed";

    @ApiModelProperty(notes = "Unidade de medida da Embalagem do item")
    public String captionPacking;

    @ApiModelProperty(notes = "Preço total do item")
    @Column(nullable = false)
    public double totalPrice;

    @ApiModelProperty(notes = "Preço unitário do item")
    public double unitaryPrice;

    @ApiModelProperty(notes = "Valor do desconto do item caso seja atacado (wholesale): 0.00 (def)")
    public double discountPrice = 0.00;

    @ApiModelProperty(notes = "Código de barra do item")
    @Column(nullable = false)
    public String barCode;

    @ApiModelProperty(notes = "Código interno do item")
    @Column(nullable = false)
    public String internalCode;

    @ApiModelProperty(notes = "Descrição do item")
    @Column(length = 100)
    public String itemDescription;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCaptionPacking() {
        return captionPacking;
    }

    public void setCaptionPacking(String captionPacking) {
        this.captionPacking = captionPacking;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(double unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
