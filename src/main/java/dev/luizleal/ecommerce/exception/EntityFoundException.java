package dev.luizleal.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EntityFoundException extends ECommerceException{

    private String title;
    private String detail;

    public EntityFoundException(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle(title);
        pb.setDetail(detail);

        return pb;
    }

}
