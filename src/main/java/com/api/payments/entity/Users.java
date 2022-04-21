package com.api.payments.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity{

    @Column(nullable = false, length = 50)
    public String name;

    @Column(nullable = false, length = 30)
    public String email;

    @Column(nullable = false, length = 30)
    public String password;

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

    public UserConfigurations getUserConfigurations() {
        return userConfigurations;
    }

    public void setUserConfigurations(UserConfigurations userConfigurations) {
        this.userConfigurations = userConfigurations;
    }
}
