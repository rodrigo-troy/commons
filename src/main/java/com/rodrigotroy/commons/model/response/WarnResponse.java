package com.rodrigotroy.commons.model.response;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 7/3/17
 * Time: 16:57
 */
public class WarnResponse implements IResponse {
    private String message;
    private String detail;

    public WarnResponse(String message) {
        this.message = message;
    }

    public WarnResponse(String message,
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
        return Outcome.WARN;
    }
}
