package com.rodrigotroy.commons.model.datatable;

import com.rodrigotroy.commons.model.list.IList;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 15:17
 */
public interface IDataTable<E extends IListHolder> extends IList<E> {
    List<Object> getTitles();

    void setTitles(List<Object> titles);

    List<Header> getHeaders();

    void setHeaders(List<Header> headers);
}
