package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @ApiModelProperty(notes = "Identificador Comum")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false, length = 16)
    private UUID id;

    @ApiModelProperty(notes = "Data de Criação Comum")
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ApiModelProperty(notes = "Data de Atualização Comum")
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
