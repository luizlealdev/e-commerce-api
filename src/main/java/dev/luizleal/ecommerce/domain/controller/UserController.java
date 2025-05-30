package dev.luizleal.ecommerce.domain.controller;


import dev.luizleal.ecommerce.domain.dto.request.UpdatePasswordRequestDto;
import dev.luizleal.ecommerce.domain.dto.request.UpdateUserRequestDto;
import dev.luizleal.ecommerce.domain.dto.response.ApiResponse;
import dev.luizleal.ecommerce.domain.dto.response.UserPrivateResponseDto;
import dev.luizleal.ecommerce.domain.dto.response.UserPublicResponseDto;
import dev.luizleal.ecommerce.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.luizleal.ecommerce.persistence.common.ApiResponseStatus.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    //@PreAuthorize("hasAuthority('TYPE_ACCESS')")
    public ResponseEntity<ApiResponse<UserPrivateResponseDto>> getPersonalData(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        var userData = userService.getPersonalData(authorization);
        var response = new ApiResponse<>(
                SUCCESS.statusCode,
                SUCCESS.status,
                "Personal data fetched successfully",
                userData
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    //@PreAuthorize("hasAuthority('TYPE_ACCESS')")
    public ResponseEntity<ApiResponse<UserPublicResponseDto>> getUserData(
            @PathVariable("id") String id
    ) {
        var userData = userService.getUserById(id);
        var response = new ApiResponse<>(
                SUCCESS.statusCode,
                SUCCESS.status,
                "User data fetched successfully",
                userData
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/all")
    //@PreAuthorize("hasAuthority('TYPE_ACCESS')")
    public ResponseEntity<ApiResponse<List<UserPublicResponseDto>>> getUserData(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit
    ) {
        var usersList = userService.getAllUsers(
                offset,
                limit
        );
        var response = new ApiResponse<>(
                SUCCESS.statusCode,
                SUCCESS.status,
                "User data fetched successfully",
                usersList
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse<UserPrivateResponseDto>> updateUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestBody @Valid UpdateUserRequestDto dto
    ) {
        var userData = userService.updateUser(authorization, dto);
        var response = new ApiResponse<>(
                SUCCESS.statusCode,
                SUCCESS.status,
                "User data updated successfully",
                userData
        );

        return ResponseEntity.ok(response);
    }

    /*@DeleteMapping("/user")
    public ResponseEntity<ApiResponse<Object>> deleteUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        userService.deleteUser(authorization);
        var response = new ApiResponse<>(
                DELETED.statusCode,
                DELETED.status,
                "User deleted successfully",
                null
        );

        return ResponseEntity.ok(response);
    }*/

    @PutMapping("/user/update-password")
    public ResponseEntity<ApiResponse<Object>> updatePassword(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestBody @Valid UpdatePasswordRequestDto dto
    ) {
        userService.updateUserPassword(authorization, dto);
        var response = new ApiResponse<>(
                UPDATED.statusCode,
                UPDATED.status,
                "Password updated successfully",
                null
        );

        return ResponseEntity.ok(response);
    }

}
