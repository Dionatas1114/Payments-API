package com.api.payments.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_configurations")
@EqualsAndHashCode(callSuper = true)
public class UserConfigurations extends BaseEntity{

    @JoinColumn(name = "user_id")
    @OneToOne()
    public UserModel userConfigurations;

    @Column(nullable = false)
    public boolean hasNotifications;

}
