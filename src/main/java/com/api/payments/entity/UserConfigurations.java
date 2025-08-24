package com.api.payments.entity;

import com.api.payments.enums.LocaleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static com.api.payments.validations.messages.UserValidatorMessages.userCannotBeNull;

@Getter
@Setter
@Builder
@Entity
@Table(name = "USER_CONFIGURATIONS")
@NoArgsConstructor()
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConfigurations extends BaseEntity {

    @ToString.Exclude
    @JsonIgnore
    @NotNull(message = userCannotBeNull)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @ApiModelProperty(notes = "Identificador do Usuário")
    public Users user;

    @Builder.Default
    @Column(name = "has_notifications", nullable = false)
    @ApiModelProperty(notes = "Notificações Ativadas para este Usuário")
    public boolean hasNotifications = true;

    @Builder.Default
    @Column(name = "language", nullable = false, length = 10)
    @ApiModelProperty(notes = "Idioma do Usuário")
    public String language = LocaleType.PORTUGUESE_BRAZIL.getCode();

    @PrePersist
    @PreUpdate
    private void validateLanguage() {
        if (!LocaleType.isValidCode(language)) {
            throw new IllegalArgumentException("Invalid language code: " + language);
        }
    }
}
