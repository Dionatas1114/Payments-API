package com.api.payments.entity;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
@EqualsAndHashCode(callSuper = true)
public class Items extends BaseEntity {

    @Column(nullable = false, length = 50)
    public String itemName;

    @Column(nullable = false, length = 50)
    public String itemType;  //product or service

    public String productBrand;
    public String category = "Other";
    public String manufacturer = "Uninformed"; //fabricante
    public String captionPacking; //KG, L

    @Column(nullable = false)
    public double totalPrice;
    public double unitaryPrice;
    public double discountPrice = 0.00; //if wholesale (atacado)

    @Column(nullable = false)
    public String barCode;

    @Column(nullable = false)
    public String internalCode;

    @Column(length = 100)
    public String description;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
