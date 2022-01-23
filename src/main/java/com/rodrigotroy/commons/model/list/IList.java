package com.rodrigotroy.commons.model.list;

import com.rodrigotroy.commons.model.datatable.IListHolder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 15-10-19
 * Time: 11:17
 */
public interface IList<E extends IListHolder> {
    java.util.List<E> getRows();

    void setRows(List<E> rows);

    E getSelectedRow();

    void setSelectedRow(E selectedRow);

    Boolean isEmpty();
}
