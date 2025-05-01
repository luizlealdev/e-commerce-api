package dev.luizleal.ecommerce.domain.dto.response;

public record UserPrivateResponseDto(String id,
                                     String firstName,
                                     String lastName,
                                     String email,
                                     String address,
                                     String role) {
}