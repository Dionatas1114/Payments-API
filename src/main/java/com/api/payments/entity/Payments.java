package com.api.payments.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYMENTS")
@EqualsAndHashCode(callSuper = true)
public class Payments extends TransactionBaseEntity {
}
