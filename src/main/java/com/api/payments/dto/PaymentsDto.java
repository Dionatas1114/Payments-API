package com.api.payments.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentsDto {

    public String debtorFullName;
    public String debtorLastName;
    public String paymentMethod;
    public Boolean paymentStatus;
    public LocalDate expirationDate;
    public LocalDate paymentDate;
    public String paymentType; //unique daily weekly monthly and yearly
    public String currency; //R$
    public double interest;
    public double fine;
    public double increasedValue;
    public double discPayAdvance;
    public double originalValue;
    public double total;
    public String description;
    public String messageText;

}
