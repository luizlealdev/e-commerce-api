package dev.luizleal.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidPropertyException extends ECommerceException {

    private String property;
    private String detail;

    public InvalidPropertyException(String property, String detail) {
        this.property = property;
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Invalid property");
        pb.setProperty("property", property);
        pb.setDetail(detail);

        return pb;
    }
}
