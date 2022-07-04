package com.api.payments.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity{

    @ApiModelProperty(notes = "Nome do Usuário")
    @Column(nullable = false, length = 50)
    public String userName;

    @ApiModelProperty(notes = "Email do Usuário")
    @Column(nullable = false, length = 30)
    public String email;

    @ApiModelProperty(notes = "Password do Usuário")
    @Column(nullable = false, length = 30)
    public String password;

    @ApiModelProperty(notes = "Configurações do Usuário")
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL)
    public UserConfigurations userConfigurations;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public UserConfigurations getUserConfigurations() {
        return userConfigurations;
    }

    public void setUserConfigurations(UserConfigurations userConfigurations) {
        this.userConfigurations = userConfigurations;
    }
}
