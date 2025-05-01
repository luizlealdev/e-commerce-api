package dev.luizleal.ecommerce.domain.dto.request;

import dev.luizleal.ecommerce.persistence.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDto(@NotBlank String firstName,
                          @NotBlank String lastName,
                          @NotBlank @Email String email,
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