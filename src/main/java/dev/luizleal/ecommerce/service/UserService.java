package dev.luizleal.ecommerce.service;

import dev.luizleal.ecommerce.dto.request.UserRequestDto;
import dev.luizleal.ecommerce.dto.response.UserResponseDto;
import dev.luizleal.ecommerce.exception.UserAlreadyExistsException;
import dev.luizleal.ecommerce.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        var existentUser = userRepository.findByEmail(userRequestDto.email());
        if (existentUser.isEmpty()) {
            throw new UserAlreadyExistsException();
        }

        var userEntity = userRequestDto.toEntity();
        userEntity.setPassword(
                passwordEncoder.encode(userRequestDto.password())
        );

        var createdUser = userRepository.save(userEntity);

        return new UserResponseDto(
                createdUser.getId().toString(),
                createdUser.getFirstName(),
                createdUser.getLastName(),
                createdUser.getEmail(),
                createdUser.getAddress(),
                createdUser.getRole().name()
        );
    }
}
