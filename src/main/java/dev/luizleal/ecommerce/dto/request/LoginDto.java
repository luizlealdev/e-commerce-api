package dev.luizleal.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank @Email String email,
        @NotBlank String password) {
}