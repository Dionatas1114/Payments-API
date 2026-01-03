package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @ApiModelProperty(notes = "Identificador único da entidade")
    @Id
    @Column(name = "id", updatable = false, unique = true, nullable = false, length = 16)
    private UUID id;

    @ApiModelProperty(notes = "Data de criação do registro")
    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ApiModelProperty(notes = "Data da última atualização do registro")
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) { // Usa o bean do Spring
            id = com.github.f4b6a3.uuid.UuidCreator.getTimeOrdered();
        }
    }
}
