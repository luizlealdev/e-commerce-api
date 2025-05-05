package dev.luizleal.ecommerce.persistence.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BANNED("BANNED");

    private String status;
}
