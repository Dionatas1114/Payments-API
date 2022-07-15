package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SERVICES")
@EqualsAndHashCode(callSuper = true)
public class Services extends ItemBaseEntity {

    @ApiModelProperty(notes = "Data em que o serviço foi realizado")
    public LocalDateTime dateServiceProvided;

    public LocalDateTime getDateServiceProvided() {
        return dateServiceProvided;
    }

    public void setDateServiceProvided(LocalDateTime dateServiceProvided) {
        this.dateServiceProvided = dateServiceProvided;
    }
}
