package com.api.payments.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_configurations")
@EqualsAndHashCode(callSuper = true)
public class UserConfigurations extends BaseEntity{

    @JoinColumn(name = "user_id")
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @NotNull
    @JsonIgnore
    public Users user;

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
