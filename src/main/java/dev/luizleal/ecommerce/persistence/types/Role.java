package dev.luizleal.ecommerce.persistence.types;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    CLIENT("CLIENT"),
    SELLER("SELLER"),
    ADMIN("ADMIN");

    private String role;
}
