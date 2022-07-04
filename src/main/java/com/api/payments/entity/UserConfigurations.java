package com.api.payments.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USER_CONFIGURATIONS")
@EqualsAndHashCode(callSuper = true)
public class UserConfigurations extends BaseEntity{

    @ApiModelProperty(notes = "Identificador do Usuário")
    @JoinColumn(name = "user_id")
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @NotNull
    @JsonIgnore
    public Users user;

    @ApiModelProperty(notes = "Notificações Ativadas para este Usuário")
    public boolean hasNotifications;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isHasNotifications() {
        return hasNotifications;
    }

    public void setHasNotifications(boolean hasNotifications) {
        this.hasNotifications = hasNotifications;
    }
}
