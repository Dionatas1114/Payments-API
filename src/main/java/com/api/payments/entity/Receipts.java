package com.api.payments.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "RECEIPTS")
@EqualsAndHashCode(callSuper = true)
public class Receipts extends TransactionBaseEntity {
}
