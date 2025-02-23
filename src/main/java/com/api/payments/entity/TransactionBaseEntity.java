package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
@Setter
@Getter
public class TransactionBaseEntity extends BaseEntity {

    @ApiModelProperty(notes = "Nome Completo do Devedor")
    @Column(nullable = false, length = 50)
    public String debtorFullName;

    @ApiModelProperty(notes = "Sobrenome do Devedor")
    @Column(nullable = false, length = 15)
    public String debtorLastName;

    @ApiModelProperty(notes = "Método de Pagamento")
    @Column(nullable = false)
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
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "BINARY(16)")
    @ManyToOne
    private Users user;

    public void setTransactionFrequency(String transactionFrequency) {
        this.transactionFrequency = !(transactionFrequency == null
                | Objects.equals(transactionFrequency, ""))
                ? transactionFrequency : "unique";
    }
}
