package dev.luizleal.ecommerce.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDto(@NotBlank String firstName,
                                   @NotBlank String lastName,
                                   @NotBlank @Email String email,
                                   @NotBlank String address) {
}