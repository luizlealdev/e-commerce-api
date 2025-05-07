package dev.luizleal.ecommerce.domain.dto.response;

import dev.luizleal.ecommerce.persistence.entity.User;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
public class UserPublicResponseDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String role;
    private Instant createdAt;

    public UserPublicResponseDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole().name();
        this.createdAt = user.getCreatedAt();
    }
}