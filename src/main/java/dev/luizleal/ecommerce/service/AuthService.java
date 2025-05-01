package dev.luizleal.ecommerce.service;

import dev.luizleal.ecommerce.dto.request.LoginDto;
import dev.luizleal.ecommerce.dto.request.RegisterDto;
import dev.luizleal.ecommerce.dto.response.JwtResponseDto;
import dev.luizleal.ecommerce.dto.response.UserResponseDto;
import dev.luizleal.ecommerce.exception.UserAlreadyExistsException;
import dev.luizleal.ecommerce.exception.InvalidCredentialsException;
import dev.luizleal.ecommerce.exception.UserNotFoundException;
import dev.luizleal.ecommerce.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;

    public UserResponseDto register(RegisterDto userRequestDto) {
        var existentUser = userRepository.findByEmail(userRequestDto.email());
        if (existentUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        var userEntity = userRequestDto.toEntity();
        userEntity.setPassword(
                passwordEncoder.encode(userRequestDto.password())
        );

        var createdUser = userRepository.save(userEntity);

        var accessToken = jwtService.generateAccessToken(createdUser);
        var refreshToken = jwtService.generateRefreshToken(createdUser);

        return new UserResponseDto(
                createdUser.getId().toString(),
                createdUser.getFirstName(),
                createdUser.getLastName(),
                createdUser.getEmail(),
                createdUser.getAddress(),
                createdUser.getRole().name(),
                new JwtResponseDto(accessToken, refreshToken)
        );
    }

    public UserResponseDto login(LoginDto loginDto) {
        var findUser = userRepository.findByEmail(loginDto.email());
        if (findUser.isEmpty()) {
            throw new InvalidCredentialsException("The user with email '" + loginDto.email() + "' was not found");
        }

        var user = findUser.get();

        if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password. Try again");
        }

        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new UserResponseDto(
                user.getId().toString(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAddress(),
                user.getRole().name(),
                new JwtResponseDto(accessToken, refreshToken)
        );
    }

    public JwtResponseDto refresh(String token) {
        var formattedToken = token.split(" ")[1];

        if (!jwtService.isTokenValid(formattedToken, "refresh")) {
            throw new InvalidBearerTokenException("The Bearer token is invalid or it's not a refresh token");
        }

        var userId = jwtService.getSubject(formattedToken);
        var user = userRepository.findById(UUID.fromString(userId));

        if (user.isEmpty()) {
            throw new UserNotFoundException("No user found related to this token");
        }

        var accessToken = jwtService.generateAccessToken(user.get());

        return new JwtResponseDto(accessToken, formattedToken);
    }
}
