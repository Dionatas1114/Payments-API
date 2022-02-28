package com.api.payments.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
@EqualsAndHashCode(callSuper = true)
public class PaymentModel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Identificação do pagamento no banco de dados")
    @Column(name = "id", updatable = false, unique = true, nullable = false, length = 16)
    private UUID id;

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

}
