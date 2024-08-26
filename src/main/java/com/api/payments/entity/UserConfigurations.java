package com.api.payments.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
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

    @ApiModelProperty(notes = "Idioma do Usuário")
    public String language = "pt_BR";

    public void setUser(Users user) {
        this.user = user;
    }

    public void setHasNotifications(boolean hasNotifications) {
        this.hasNotifications = hasNotifications;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
