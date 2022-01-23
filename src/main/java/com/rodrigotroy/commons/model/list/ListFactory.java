package com.rodrigotroy.commons.model.list;

import com.rodrigotroy.commons.model.IListModel;
import com.rodrigotroy.commons.model.datatable.DataTable;
import com.rodrigotroy.commons.model.datatable.IDomainObjectMapper;
import com.rodrigotroy.commons.model.datatable.IListHolder;
import com.rodrigotroy.commons.util.Validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 04-03-20
 * Time: 13:29
 */
public class ListFactory {
    public static <T extends IListHolder> IList<T> createList(IListModel listModel,
                                                              IDomainObjectMapper<T> domainObjectMapper) {
        IList<T> list = new DataTable<>();

        if (domainObjectMapper == null) {
            throw new NullPointerException("NO EXISTE UN MAPPER");
        }

        if (Validator.validateList(listModel.getSelectedRow())) {
            list.setSelectedRow(domainObjectMapper.createObject(listModel.getSelectedRow()));
        }

        for (List<Object> row : listModel.getRows()) {
            list.getRows().add(domainObjectMapper.createObject(row));
        }

        return list;
    }
}
