package com.rodrigotroy.commons.model.datatable;

import com.rodrigotroy.commons.model.IComplexHeaderDataTableModel;
import com.rodrigotroy.commons.model.IDataTableModel;
import com.rodrigotroy.commons.util.Validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 04-03-20
 * Time: 13:13
 */
public class DataTableFactory {
    public static <T extends IListHolder> ComplexHeaderDataTable<T> createComplexHeaderDatatable(IComplexHeaderDataTableModel dataTableModel,
                                                                                                 IDomainObjectMapper<T> domainObjectMapper) {
        return DataTableFactory.createComplexHeaderDatatable(dataTableModel,
                                                             new DefaultHeaderBuilder(),
                                                             new HeaderLayoutBuilder(),
                                                             domainObjectMapper);
    }

    public static <T extends IListHolder> ComplexHeaderDataTable<T> createComplexHeaderDatatable(IComplexHeaderDataTableModel dataTableModel,
                                                                                                 IHeaderBuilder headerBuilder,
                                                                                                 IHeaderLayoutBuilder headerLayoutBuilder,
                                                                                                 IDomainObjectMapper<T> domainObjectMapper) {
        ComplexHeaderDataTable<T> dataTable = new ComplexHeaderDataTable<>();

        if (domainObjectMapper == null) {
            throw new NullPointerException("NO EXISTE UN MAPPER");
        }

        if (Validator.validateList(dataTableModel.getTitles())) {
            dataTable.setTitles(dataTableModel.getTitles().get(0));
        }

        if (Validator.validateList(dataTableModel.getSelectedRow())) {
            dataTable.setSelectedRow(domainObjectMapper.createObject(dataTableModel.getSelectedRow()));
        }

        for (List<Object> header : dataTableModel.getHeaders()) {
            dataTable.getHeaders().add(headerBuilder.createHeader(header));
        }

        for (List<Object> row : dataTableModel.getRows()) {
            dataTable.getRows().add(domainObjectMapper.createObject(row));
        }

        for (List<Object> header : dataTableModel.getHeaderLayout()) {
            dataTable.getHeaderLayouts().add(headerLayoutBuilder.createHeaderLayout(header));
        }

        return dataTable;
    }

    public static <T extends IListHolder> IDataTable<T> createDatatable(IDataTableModel dataTableModel,
                                                                        IDomainObjectMapper<T> domainObjectMapper) {
        return DataTableFactory.createDatatable(dataTableModel,
                                                new DefaultHeaderBuilder(),
                                                domainObjectMapper);
    }

    public static <T extends IListHolder> IDataTable<T> createDatatable(IDataTableModel dataTableModel,
                                                                        IHeaderBuilder headerBuilder,
                                                                        IDomainObjectMapper<T> domainObjectMapper) {
        DataTable<T> dataTable = new DataTable<T>();

        if (domainObjectMapper == null) {
            throw new NullPointerException("NO EXISTE UN MAPPER");
        }

        if (Validator.validateList(dataTableModel.getTitles())) {
            dataTable.setTitles(dataTableModel.getTitles().get(0));
        }

        if (Validator.validateList(dataTableModel.getSelectedRow())) {
            dataTable.setSelectedRow(domainObjectMapper.createObject(dataTableModel.getSelectedRow()));
        }

        for (List<Object> header : dataTableModel.getHeaders()) {
            dataTable.getHeaders().add(headerBuilder.createHeader(header));
        }

        for (List<Object> row : dataTableModel.getRows()) {
            dataTable.getRows().add(domainObjectMapper.createObject(row));
        }

        return dataTable;
    }
}
