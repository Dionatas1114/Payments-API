package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
@Getter
public class TransactionBaseEntity extends BaseEntity {

    @ApiModelProperty(notes = "Nome Completo do Devedor")
    @Column(nullable = false, length = 50)
    public String debtorFullName;

    @ApiModelProperty(notes = "Sobrenome do Devedor")
    @Column(nullable = false, length = 15)
    public String debtorLastName;

    @ApiModelProperty(notes = "Método de Pagamento")
    @Column(name = "payment_Method", nullable = false)
    public String paymentMethod;

    @ApiModelProperty(notes = "Status de Pagamento")
    @Column(nullable = false)
    public Boolean paymentStatus;

    @ApiModelProperty(notes = "Data de Expiração do Recebimento")
    @Column(nullable = false)
    public LocalDate expirationDate;

    @ApiModelProperty(notes = "Data da Transação")
    public LocalDate paymentDate;

    @ApiModelProperty(notes = "Frequência da Transação: unique (def) - [unique, daily, weekly, monthly or yearly]")
    @Column(nullable = false, length = 10)
    public String transactionFrequency = "unique";

    @ApiModelProperty(notes = "Moeda Utilizada na Transação: BRL/R$ (def)")
    public String currency = "BRL";

    @ApiModelProperty(notes = "Valor dos Juros: 0.00 (def)")
    public double interest = 0.00;

    @ApiModelProperty(notes = "Valor das Multas: 0.00 (def)")
    public double fine = 0.00;

    @ApiModelProperty(notes = "Valor Resultante de Juros e Multas: 0.00 (def)")
    public double increasedValue = 0.00;

    @ApiModelProperty(notes = "Valor do Desconto por Pagamento Adiantado: 0.00 (def)")
    public double discPayAdvance = 0.00;

    @ApiModelProperty(notes = "Valor Original da Transação")
    @Column(nullable = false)
    public double originalValue;

    @ApiModelProperty(notes = "Valor Total da Transação")
    @Column(nullable = false)
    public double total;

    @ApiModelProperty(notes = "Descrição da Transação")
    @Column(length = 50)
    public String description = "None";

    @ApiModelProperty(notes = "Mensagem de texto da Transação")
    public String messageText = "None";

    @ApiModelProperty(notes = "Usuário da Transação")
    @JoinColumn(name = "user_id")
    @ManyToOne
    private Users user;

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

    public String getTransactionFrequency() {
        return transactionFrequency;
    }

    public void setTransactionFrequency(String transactionFrequency) {
        this.transactionFrequency = !(transactionFrequency == null
                | Objects.equals(transactionFrequency, ""))
                ? transactionFrequency : "unique";
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
