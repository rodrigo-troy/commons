package com.rodrigotroy.commons.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/14/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IListModel {
    List<List<Object>> getRows();

    void setRows(List<List<Object>> rows);

    List<Object> getSelectedRow();

    void setSelectedRow(List<Object> row);
}
