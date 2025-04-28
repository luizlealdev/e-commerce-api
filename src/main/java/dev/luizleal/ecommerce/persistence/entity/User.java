package dev.luizleal.ecommerce.persistence.entity;

import dev.luizleal.ecommerce.persistence.types.Role;
import dev.luizleal.ecommerce.persistence.types.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.Instant;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "role", length = 12)
    private Role role = Role.CLIENT;

    @Column(name = "status", length = 12)
    private Status status = Status.ACTIVE;

    @Column(name = "created_at")
    @CurrentTimestamp
    private Instant createdAt;

    public User(
            String firstName,
            String lastName,
            String email,
            String password,
            String address
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public boolean isActive() {
        return this.role.name().equals(Status.ACTIVE);
    }
}
