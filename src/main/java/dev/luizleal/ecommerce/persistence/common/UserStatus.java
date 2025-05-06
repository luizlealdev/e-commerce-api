package dev.luizleal.ecommerce.persistence.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DELETED("DELETED"),
    BANNED("BANNED");

    private String status;

    public static boolean isActive(UserStatus status) {
        return UserStatus.ACTIVE == status;
    }
}
