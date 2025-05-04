package dev.luizleal.ecommerce.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateUserRequestDto(@NotBlank @Length(min = 3, max = 50) String firstName,
                                   @NotBlank @Length(min = 3, max = 50) String lastName,
                                   @NotBlank @Length(min = 5, max = 100) @Email String email,
                                   @NotBlank String address,
                                   @NotBlank String role) {
}