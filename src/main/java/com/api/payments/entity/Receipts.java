package com.api.payments.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receipts")
@EqualsAndHashCode(callSuper = true)
public class Receipts extends BaseEntity {

    @Column(nullable = false, length = 50)
    public String debtorFullName;

    @Column(nullable = false, length = 15)
    public String debtorLastName;

    @Column(nullable = false)
    public String paymentMethod;

    @Column(nullable = false)
    public Boolean paymentStatus;

    @Column(nullable = false)
    public LocalDate expirationDate;
    public LocalDate paymentDate;

    @Column(nullable = false, length = 10)
    public String receiptType = "unique"; //unique daily weekly monthly and yearly
    public String currency = "BRL"; //R$
    public double interest = 0.00;
    public double fine = 0.00;
    public double increasedValue = 0.00;
    public double discPayAdvance = 0.00;

    @Column(nullable = false)
    public double originalValue;
    public double total;

    @Column(length = 50)
    public String description;
    public String messageText;

    public String getDebtorFullName() {
        return debtorFullName;
    }

    public void setDebtorFullName(String debtorFullName) {
        this.debtorFullName = debtorFullName;
    }

    public String getDebtorLastName() {
        return debtorLastName;
    }

    public void setDebtorLastName(String debtorLastName) {
        this.debtorLastName = debtorLastName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public double getIncreasedValue() {
        return increasedValue;
    }

    public void setIncreasedValue(double increasedValue) {
        this.increasedValue = increasedValue;
    }

    public double getDiscPayAdvance() {
        return discPayAdvance;
    }

    public void setDiscPayAdvance(double discPayAdvance) {
        this.discPayAdvance = discPayAdvance;
    }

    public double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(double originalValue) {
        this.originalValue = originalValue;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
