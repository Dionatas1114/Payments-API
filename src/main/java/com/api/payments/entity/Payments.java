package com.api.payments.entity;

import com.api.payments.enums.PaymentMethodTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
