package dev.luizleal.ecommerce.persistence.types;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BANNED("BANNED");

    private String status;
}
