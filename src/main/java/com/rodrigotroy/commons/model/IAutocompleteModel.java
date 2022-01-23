package com.rodrigotroy.commons.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 11/15/12
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IAutocompleteModel {

    public List<List<Object>> getHeaders();

    public void setHeaders(List<List<Object>> headers);

    public List<List<Object>> getRows();

    public void setRows(List<List<Object>> rows);

    public List<List<Object>> getTitles();

    public void setTitles(List<List<Object>> titles);

    public Object getSelectedObject();

    public void setSelectedObject(Object selectedObject);

}
