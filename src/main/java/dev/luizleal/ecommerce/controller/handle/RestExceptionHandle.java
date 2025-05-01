package dev.luizleal.ecommerce.controller.handle;

import dev.luizleal.ecommerce.exception.ECommerceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandle {

    @ExceptionHandler(ECommerceException.class)
    public ProblemDetail handleECommerceException(ECommerceException exception) {
        return exception.toProblemDetail();
    }

    @ExceptionHandler(InvalidBearerTokenException.class)
    public ProblemDetail handleInvalidBearerTokenException(InvalidBearerTokenException exception) {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Invalid JWT token");
        pb.setDetail(exception.getMessage());

        return pb;
    }

}
