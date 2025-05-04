package dev.luizleal.ecommerce.domain.dto.response;

public record UserPublicResponseDto(String id,
                                    String firstName,
                                    String lastName,
                                    String role) {
}