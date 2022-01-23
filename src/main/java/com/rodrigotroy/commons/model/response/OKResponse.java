package com.rodrigotroy.commons.model.response;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 23-05-16
 * Time: 18:17
 */
public class OKResponse implements IResponse {
    private final String message;
    private String detail;

    public OKResponse(String message) {
        this.message = message;
    }

    public OKResponse(String message,
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
        return Outcome.OK;
    }
}
