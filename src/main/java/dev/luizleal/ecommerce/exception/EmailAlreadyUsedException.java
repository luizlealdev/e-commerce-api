package dev.luizleal.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EmailAlreadyUsedException extends ECommerceException{
    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Email already used");
        pb.setDetail("A user with this email has already been registered");

        return pb;
    }
}
