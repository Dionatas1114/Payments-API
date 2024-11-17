package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity {

    @ApiModelProperty(notes = "Nome do Usuário")
    @Column(nullable = false, length = 50)
    public String name;

    @ApiModelProperty(notes = "Email do Usuário")
    @Column(nullable = false, length = 30)
    public String email;

    @ApiModelProperty(notes = "Password do Usuário")
    @Column(nullable = false, length = 100)
    public String password;

    @ApiModelProperty(notes = "Telefone do Usuário")
    @Column(nullable = false, length = 30)
    public String phone;

    @ApiModelProperty(notes = "Configurações do Usuário")
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL)
    public UserConfigurations userConfigurations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserConfigurations getUserConfigurations() {
        return userConfigurations;
    }

    public void setUserConfigurations(UserConfigurations userConfigurations) {
        this.userConfigurations = userConfigurations;
    }
}
