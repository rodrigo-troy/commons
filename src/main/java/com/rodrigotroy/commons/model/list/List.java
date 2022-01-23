package com.rodrigotroy.commons.model.list;

import com.rodrigotroy.commons.model.datatable.IListHolder;
import com.rodrigotroy.commons.util.Validator;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 15-10-19
 * Time: 11:16
 */
public class List<E extends IListHolder> implements IList<E> {
    private java.util.List<E> rows;
    private E selectedRow;

    @Override
    public java.util.List<E> getRows() {
        if (rows == null) {
            rows = new ArrayList<>();
        }

        return rows;
    }

    @Override
    public void setRows(java.util.List<E> rows) {
        this.rows = rows;
    }

    @Override
    public E getSelectedRow() {
        return this.selectedRow;
    }

    @Override
    public void setSelectedRow(E selectedRow) {
        this.selectedRow = selectedRow;
    }

    @Override
    public Boolean isEmpty() {
        return !Validator.validateList(this.rows);
    }
}
