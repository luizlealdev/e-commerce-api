package dev.luizleal.ecommerce.persistence.entity;

import dev.luizleal.ecommerce.persistence.common.UserRole;
import dev.luizleal.ecommerce.persistence.common.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "role", length = 12)
    private UserRole role = UserRole.CLIENT;

    @Column(name = "status", length = 12)
    private UserStatus status = UserStatus.ACTIVE;

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
        return this.role.name().equals(UserStatus.ACTIVE);
    }
}
