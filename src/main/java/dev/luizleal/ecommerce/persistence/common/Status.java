package dev.luizleal.ecommerce.persistence.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BANNED("BANNED");

    private String status;
}
