package dev.luizleal.ecommerce.service;

import dev.luizleal.ecommerce.dto.request.RegisterDto;
import dev.luizleal.ecommerce.dto.response.JwtResponseDto;
import dev.luizleal.ecommerce.dto.response.UserResponseDto;
import dev.luizleal.ecommerce.exception.UserAlreadyExistsException;
import dev.luizleal.ecommerce.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;

}
