package dev.luizleal.ecommerce.domain.controller;

import dev.luizleal.ecommerce.domain.dto.request.LoginDto;
import dev.luizleal.ecommerce.domain.dto.request.RegisterDto;
import dev.luizleal.ecommerce.domain.dto.response.ApiResponse;
import dev.luizleal.ecommerce.domain.dto.response.JwtResponseDto;
import dev.luizleal.ecommerce.domain.dto.response.UserResponseDto;
import dev.luizleal.ecommerce.domain.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static dev.luizleal.ecommerce.persistence.common.ApiResponseStatus.CREATED;
import static dev.luizleal.ecommerce.persistence.common.ApiResponseStatus.SUCCESS;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> register(
            @RequestBody @Valid RegisterDto dto
    ) {
        var createdUser = authService.register(dto);
        var response = new ApiResponse<>(
                CREATED.statusCode,
                CREATED.status,
                "User created successfully",
                createdUser
        );

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> login(
            @RequestBody @Valid LoginDto dto
    ) {
        var userLogged = authService.login(dto);
        var response = new ApiResponse<>(
                SUCCESS.statusCode,
                SUCCESS.status,
                "User logged successfully",
                userLogged
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ApiResponse<JwtResponseDto>> refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        var authTokens = authService.refresh(authorization);
        var response = new ApiResponse<>(
                SUCCESS.statusCode,
                SUCCESS.status,
                "Access token refreshed successfully",
                authTokens
        );

        return ResponseEntity.ok(response);
    }
}
