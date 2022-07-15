package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
@EqualsAndHashCode(callSuper = true)
public class Products extends ItemBaseEntity {

    @ApiModelProperty(notes = "Marca do produto")
    public String productBrand;

    @ApiModelProperty(notes = "Fabricante do produto: Uninformed (def)")
    public String manufacturer = "Uninformed";

    @ApiModelProperty(notes = "Unidade de medida da Embalagem do produto")
    public String captionPacking;

    @ApiModelProperty(notes = "Código de barra do produto")
    @Column(nullable = false)
    public String barCode;

    @ApiModelProperty(notes = "Preço unitário do item")
    public double unitaryPrice;

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
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

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public double getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(double unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }
}
