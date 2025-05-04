package dev.luizleal.ecommerce.domain.service;

import dev.luizleal.ecommerce.domain.dto.request.UpdatePasswordRequestDto;
import dev.luizleal.ecommerce.domain.dto.request.UpdateUserRequestDto;
import dev.luizleal.ecommerce.domain.dto.response.UserPrivateResponseDto;
import dev.luizleal.ecommerce.domain.dto.response.UserPublicResponseDto;
import dev.luizleal.ecommerce.exception.EmailAlreadyUsedException;
import dev.luizleal.ecommerce.exception.InvalidCredentialsException;
import dev.luizleal.ecommerce.exception.UserNotFoundException;
import dev.luizleal.ecommerce.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private JwtService jwtService;
    @Autowired private PasswordEncoder passwordEncoder;

    public UserPublicResponseDto getUserById(String userId) {
        var userOptional = userRepository.findById(UUID.fromString(userId));
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with id '" + userId + "' was not found");
        }

        var user = userOptional.get();

        return new UserPublicResponseDto(
                user.getId().toString(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name()
        );

    }

    public UserPrivateResponseDto getPersonalData(String authorization) {
        var token = authorization.split(" ")[1];
        var sub = jwtService.getSubject(token);

        var userOptional = userRepository.findById(UUID.fromString(sub));
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("No user found related to this token");
        }

        var user = userOptional.get();

        return new UserPrivateResponseDto(
                user.getId().toString(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAddress(),
                user.getRole().name()
        );
    }

    public List<UserPublicResponseDto> getAllUsers(int offset, int limit) {
        var pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        var users = userRepository.findAllByOrderByCreatedAtDesc(pageRequest);

        return users.map(usr -> new UserPublicResponseDto(
                usr.getId().toString(),
                usr.getFirstName(),
                usr.getLastName(),
                usr.getRole().name()
        )).getContent();
    }

    public UserPrivateResponseDto updateUser(String authorization, UpdateUserRequestDto dto) {
        var token = authorization.split(" ")[1];
        var sub = jwtService.getSubject(token);

        var user = userRepository.findById(UUID.fromString(sub));
        if (user.isEmpty()) {
            throw new UserNotFoundException("No user found related to this token");
        }

        var existentEmail = userRepository.findByEmail(dto.email());
        if (existentEmail.isPresent() && !Objects.equals(existentEmail.get().getEmail(), user.get().getEmail())) {
            throw new EmailAlreadyUsedException();
        }

        var entity = user.get();
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setAddress(dto.address());

        userRepository.save(entity);

        return new UserPrivateResponseDto(
                entity.getId().toString(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getRole().name()
        );
    }

    public void updateUserPassword(String authorization, UpdatePasswordRequestDto dto) {
        var token = authorization.split(" ")[1];
        var sub = jwtService.getSubject(token);

        var user = userRepository.findById(UUID.fromString(sub));
        if (user.isEmpty()) {
            throw new UserNotFoundException("No user found related to this token");
        }

        var entity = user.get();

        if (!passwordEncoder.matches(dto.oldPassword(), entity.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password. Try again");
        }

        entity.setPassword(
                passwordEncoder.encode(dto.newPassword())
        );

        userRepository.save(entity);
    }
}
