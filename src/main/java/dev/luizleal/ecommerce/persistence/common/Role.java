package dev.luizleal.ecommerce.persistence.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    CLIENT("CLIENT"),
    SELLER("SELLER"),
    ADMIN("ADMIN");

    private String role;
}
