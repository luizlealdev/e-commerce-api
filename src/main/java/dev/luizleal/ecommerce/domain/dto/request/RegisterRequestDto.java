package dev.luizleal.ecommerce.domain.dto.request;

import dev.luizleal.ecommerce.persistence.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterRequestDto(@NotBlank @Length(min = 3, max = 50) String firstName,
                                 @NotBlank @Length(min = 3, max = 50) String lastName,
                                 @NotBlank @Length(min = 5, max = 100) @Email String email,
                                 @NotBlank String password,
                                 @NotBlank String address) {

    public User toEntity() {
        return new User(
                firstName,
                lastName,
                email,
                password,
                address
        );
    }
}