package dev.luizleal.ecommerce.controller;

import dev.luizleal.ecommerce.dto.request.UserRequestDto;
import dev.luizleal.ecommerce.dto.response.UserResponseDto;
import dev.luizleal.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto dto) {
        var createdUser = userService.createUser(dto);
        return ResponseEntity.status(201).body(createdUser);
    }
}
