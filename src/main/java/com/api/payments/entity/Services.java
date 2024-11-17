package com.api.payments.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "SERVICES")
@EqualsAndHashCode(callSuper = true)
public class Services extends ItemBaseEntity {
}
