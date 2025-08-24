package com.api.payments.entity;

import com.api.payments.enums.PaymentMethodTypes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "payments")
public class Payments extends TransactionBaseEntity {

    @PrePersist
    @PreUpdate
    private void validateLanguage() {
        try {
            PaymentMethodTypes.valueOf(paymentMethod);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid paymentMethod type: " + paymentMethod, e);
        }
    }
}
