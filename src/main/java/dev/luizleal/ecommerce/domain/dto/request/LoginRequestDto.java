package dev.luizleal.ecommerce.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginRequestDto(
        @NotBlank @Email @Length(min = 5, max = 100) String email,
        @NotBlank String password) {
}