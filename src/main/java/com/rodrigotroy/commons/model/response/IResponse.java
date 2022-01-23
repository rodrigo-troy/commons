package com.rodrigotroy.commons.model.response;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 29-03-16
 * Time: 15:49
 */
public interface IResponse {
    String getMessage();

    String getDetail();

    Outcome getOutcome();
}
