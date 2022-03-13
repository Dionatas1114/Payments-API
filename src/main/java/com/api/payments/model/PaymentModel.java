package com.api.payments.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
@EqualsAndHashCode(callSuper = true)
public class PaymentModel extends BaseEntity {

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
    public String paymentType = "unique"; //unique daily weekly monthly and yearly
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
