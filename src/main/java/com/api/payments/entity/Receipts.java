package com.api.payments.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RECEIPTS")
@EqualsAndHashCode(callSuper = true)
public class Receipts extends TransactionBaseEntity {
}
