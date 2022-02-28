package com.api.payments.model;

import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserModel extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false, length = 16)
    private UUID id;

    @Column(nullable = false, length = 50)
    public String name;

    @Column(nullable = false, length = 30)
    public String email;

    @Column(nullable = false, length = 30)
    public String password;

}
