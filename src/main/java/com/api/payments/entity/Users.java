package com.api.payments.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor()
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Users extends BaseEntity {

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    @ApiModelProperty(notes = "Nome do Usuário")
    public String name;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    @ApiModelProperty(notes = "Email do Usuário")
    public String email;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    @JsonIgnore
    @ApiModelProperty(notes = "Password do Usuário")
    public String password;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false)
    @ApiModelProperty(notes = "Telefone do Usuário")
    public String phone;

    @ToString.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ApiModelProperty(notes = "Configurações do Usuário")
    public UserConfigurations userConfigurations;
}
