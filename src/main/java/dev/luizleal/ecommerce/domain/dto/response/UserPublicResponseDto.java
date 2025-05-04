package dev.luizleal.ecommerce.domain.dto.response;

import java.time.Instant;

public record UserPublicResponseDto(String id,
                                    String firstName,
                                    String lastName,
                                    String role,
                                    Instant createdAt) {
}