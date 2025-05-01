package dev.luizleal.ecommerce.domain.dto.response;

public record JwtResponseDto(String accessToken,
                             String refreshToken) {
}
