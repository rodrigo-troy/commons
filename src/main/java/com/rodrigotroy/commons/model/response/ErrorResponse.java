package com.rodrigotroy.commons.model.response;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 27-04-16
 * Time: 16:48
 */
public class ErrorResponse implements IResponse {
    private String message;
    private String detail;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message,
                         String detail) {
        this.message = message;
        this.detail = detail;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetail() {
        return this.detail;
    }

    @Override
    public Outcome getOutcome() {
        return Outcome.ERROR;
    }
}
