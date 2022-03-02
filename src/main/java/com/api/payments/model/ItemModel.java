package com.api.payments.model;

import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
@EqualsAndHashCode(callSuper = true)
public class ItemModel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false, length = 16)
    private UUID id;

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

}
