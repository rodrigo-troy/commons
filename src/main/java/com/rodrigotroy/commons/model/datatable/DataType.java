package com.rodrigotroy.commons.model.datatable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 4/5/17
 * Time: 18:29
 */
public enum DataType {
    UNKNOWN(-9999),
    STRING(1),
    NUMBER(2),
    PERCENTAGE(3),
    DATE(4),
    IMAGE(5),
    BOOLEAN(6),
    DUMMY_RUT(100),
    DUMMY_NOMBRE_CLIENTE(101);

    private Integer value;

    DataType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
