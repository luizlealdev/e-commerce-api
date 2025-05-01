package dev.luizleal.ecommerce.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {

    private Integer statusCode;
    private String status;
    private String message;
    private T data;

}
