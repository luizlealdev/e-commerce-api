package dev.luizleal.ecommerce.persistence.common;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Role {
    CLIENT("CLIENT"),
    SELLER("SELLER"),
    ADMIN("ADMIN");

    private String role;

    public static boolean isValid(String value) {
        return Arrays.stream(Role.values())
                .anyMatch(r -> r.role.equalsIgnoreCase(value));
    }

    public static boolean isClientOrSeller(String value) {
        return Arrays.stream(Role.values())
                .filter(r -> !r.role.equalsIgnoreCase("ADMIN"))
                .anyMatch(r -> r.role.equalsIgnoreCase(value));
    }
}
