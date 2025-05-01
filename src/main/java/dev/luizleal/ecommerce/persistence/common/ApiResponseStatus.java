package dev.luizleal.ecommerce.persistence.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApiResponseStatus {
    SUCCESS(200, "Success"),
    CREATED(201, "Created"),
    UPDATED(200, "Updated"),
    DELETED(200, "Deleted");

    public Integer statusCode;
    public String status;
}
