package com.api.payments.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Entity
@Table(name = "SERVICES")
@EqualsAndHashCode(callSuper = true)
public class Services extends ItemBaseEntity {
}
