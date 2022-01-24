package com.rodrigotroy.commons.domain;

import com.rodrigotroy.commons.model.IEnum;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 2019-05-09
 * Time: 19:51
 */
public enum Option implements IEnum {
    SI(1,
       "Si"),
    NO(0,
       "No");

    private final Integer id;
    private final String label;

    Option(Integer id,
           String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return "Opcion{" +
               "id=" + id +
               ", label='" + label + '\'' +
               '}';
    }
}
