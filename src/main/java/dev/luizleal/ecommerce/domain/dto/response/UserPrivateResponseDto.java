package dev.luizleal.ecommerce.domain.dto.response;

import dev.luizleal.ecommerce.persistence.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserPrivateResponseDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String role;
    private Instant createdAt;

    public UserPrivateResponseDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.role = user.getRole().name();
        this.createdAt = user.getCreatedAt();
    }
}