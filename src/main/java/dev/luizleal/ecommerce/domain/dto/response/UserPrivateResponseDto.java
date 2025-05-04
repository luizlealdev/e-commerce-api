package dev.luizleal.ecommerce.domain.dto.response;

import java.time.Instant;

public record UserPrivateResponseDto(String id,
                                     String firstName,
                                     String lastName,
                                     String email,
                                     String address,
                                     String role,
                                     Instant createdAt) {
}