package dev.luizleal.ecommerce.controller;

import dev.luizleal.ecommerce.dto.request.LoginDto;
import dev.luizleal.ecommerce.dto.request.RegisterDto;
import dev.luizleal.ecommerce.dto.response.UserResponseDto;
import dev.luizleal.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid RegisterDto dto) {
        var createdUser = authService.register(dto);

        return ResponseEntity.status(201).body(createdUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody @Valid LoginDto dto) {
        var userLogged = authService.login(dto);

        return ResponseEntity.ok(userLogged);
    }
}
