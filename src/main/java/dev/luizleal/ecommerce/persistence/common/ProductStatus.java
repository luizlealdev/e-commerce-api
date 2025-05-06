package dev.luizleal.ecommerce.persistence.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    OUT_OF_STOCK("OUT_OF_STOCK"),
    DELETED("DELETED");

    private String status;
}
