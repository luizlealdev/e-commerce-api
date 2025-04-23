package dev.luizleal.ecommerce.persistence.entity;

import dev.luizleal.ecommerce.persistence.types.Role;
import dev.luizleal.ecommerce.persistence.types.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Getter
@Setter
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address", length = 80)
    private String address;

    @Column(name = "role", length = 12)
    private Role role = Role.CLIENT;

    @Column(name = "status", length = 12)
    private Status status = Status.ACTIVE;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

}
