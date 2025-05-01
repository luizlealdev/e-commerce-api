package dev.luizleal.ecommerce.service;

import dev.luizleal.ecommerce.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private JwtDecoder jwtDecoder;

    public String generateJwtToken(String subject, Long expiresIn, Map<String, Object> claims) {
        var now = Instant.now();

        var claimsSet = JwtClaimsSet.builder()
                .issuer("www.luizleal.dev")
                .subject(subject)
                .claims(c -> c.putAll(claims))
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();
        var jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();

        return jwtToken;
    }

    public String generateAccessToken(User user) {
        return generateJwtToken(
                user.getId().toString(),
                300L,
                Map.of("type", "access",
                        "scope", user.getRole().name())
        );
    }

    public String generateRefreshToken(User user) {
        return generateJwtToken(user.getId().toString(), 604800L, Map.of("type", "refresh"));
    }

    public String getSubject(String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    public boolean isTokenValid(String token, String expectedType) {
        try {
            var jwt = jwtDecoder.decode(token);
            return jwt.getClaim("type").equals(expectedType);
        } catch (JwtException e) {
            return false;
        }
    }

}
