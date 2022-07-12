package com.api.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentsDto {

    public UUID id;
    public String debtorFullName;
    public String debtorLastName;
    public String paymentMethod;
    public Boolean paymentStatus;
    public LocalDate expirationDate;
    public LocalDate paymentDate;
    public String transactionFrequency;
    public String currency;
    public double interest;
    public double fine;
    public double increasedValue;
    public double discPayAdvance;
    public double originalValue;
    public double total;
    public String description;
    public String messageText;
    public UsersDto user;

}
