package com.api.payments.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "user_configurations")
@EqualsAndHashCode(callSuper = true)
public class UserConfigurations extends BaseEntity{

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne()
    @JsonIgnore
    public Users user;

    @Column(nullable = false)
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
