package dev.luizleal.ecommerce.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequestDto(
        @NotBlank String oldPassword,
        @NotBlank String newPassword) {
}