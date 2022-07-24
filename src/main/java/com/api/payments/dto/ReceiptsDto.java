package com.api.payments.dto;

import lombok.*;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
public class ReceiptsDto extends TransactionBaseDto {
}
