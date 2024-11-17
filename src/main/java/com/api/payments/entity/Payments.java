package com.api.payments.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "PAYMENTS")
@EqualsAndHashCode(callSuper = true)
public class Payments extends TransactionBaseEntity {
}
