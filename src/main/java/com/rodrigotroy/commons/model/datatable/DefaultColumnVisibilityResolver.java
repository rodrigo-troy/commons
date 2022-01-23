package com.rodrigotroy.commons.model.datatable;

import com.rodrigotroy.util.Validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 04-03-20
 * Time: 15:42
 */
public class DefaultColumnVisibilityResolver implements IColumnVisibilityResolver {
    @Override
    public Boolean isVisible(List<Object> list) {
        return Validator.validateNumber(list.get(4)) && !Integer.valueOf(list.get(4).toString()).equals(0);
    }

    @Override
    public Boolean isDetailVisible(List<Object> list) {
        return Validator.validateNumber(list.get(4)) && !Integer.valueOf(list.get(4).toString()).equals(0);
    }
}
