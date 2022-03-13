package com.api.payments.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserModel extends BaseEntity{

    @Column(nullable = false, length = 50)
    public String name;

    @Column(nullable = false, length = 30)
    public String email;

    @Column(nullable = false, length = 30)
    public String password;

}
