package com.rodrigotroy.commons.model.datatable;

import com.rodrigotroy.commons.util.Validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 04-03-20
 * Time: 15:48
 */
public class DetailedColumnVisibilityResolver implements IColumnVisibilityResolver {
    @Override
    public Boolean isVisible(List<Object> list) {
        return Validator.validateNumber(list.get(4)) &&
               Integer.valueOf(list.get(4).toString()).equals(1);
    }

    @Override
    public Boolean isDetailVisible(List<Object> list) {
        return Validator.validateNumber(list.get(4)) &&
               Integer.parseInt(list.get(4).toString()) > 1;
    }
}
