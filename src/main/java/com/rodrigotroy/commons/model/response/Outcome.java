package com.rodrigotroy.commons.model.response;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 29-03-16
 * Time: 15:49
 */
public enum Outcome {
    OK(0),
    ERROR(1),
    WARN(2),
    NONE(-1);

    private final Integer value;

    Outcome(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
