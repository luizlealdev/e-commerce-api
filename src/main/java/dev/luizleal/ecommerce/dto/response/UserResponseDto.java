package dev.luizleal.ecommerce.dto.response;

public record UserResponseDto(String id,
                              String firstName,
                              String lastName,
                              String email,
                              String address,
                              String role) {
}
