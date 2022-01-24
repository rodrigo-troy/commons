package com.rodrigotroy.commons.model;

import com.rodrigotroy.commons.model.datatable.*;
import com.rodrigotroy.commons.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 21-10-21
 * Time: 11:23
 */
public class DataTableFactoryTest {
    private static final Logger LOG = LogManager.getLogger(DataTableFactoryTest.class);

    public void test() throws
                       Exception {
        final IDataTableModel dataTableModel = DataTableModelBuilder.createDatatable()
                                                                    .setTitles(new Object[]{100, "Titulo", "Subtitulo"})
                                                                    .addColumn("Oculta",
                                                                               DataType.NUMBER,
                                                                               2,
                                                                               false,
                                                                               false,
                                                                               true,
                                                                               0.0)
                                                                    .addColumn("Foto",
                                                                               DataType.IMAGE,
                                                                               0,
                                                                               false,
                                                                               true,
                                                                               true,
                                                                               0.0)
                                                                    .addColumn("UF",
                                                                               DataType.NUMBER,
                                                                               2,
                                                                               false,
                                                                               true,
                                                                               true,
                                                                               0.0)
                                                                    .addColumn("Nombre",
                                                                               DataType.STRING,
                                                                               0,
                                                                               false,
                                                                               true,
                                                                               true,
                                                                               0.0)
                                                                    .addColumn("Monto",
                                                                               DataType.NUMBER,
                                                                               0,
                                                                               false,
                                                                               true,
                                                                               false,
                                                                               0.0)
                                                                    .addColumn("Porcentaje",
                                                                               DataType.PERCENTAGE,
                                                                               0,
                                                                               false,
                                                                               true,
                                                                               false,
                                                                               0.0)
                                                                    .addRandomRows(10)
                                                                    .build();

        IDataTable<DefaultListHolder> datatable = DataTableFactory.createDatatable(dataTableModel,
                                                                                   new DefaultHeaderBuilder(new DefaultColumnVisibilityResolver()),
                                                                                   new DefaultDomainObjectMapper());

        for (Header header : datatable.getHeaders()) {
            if (header.isVisible()) {
                LOG.info(header.getHeaderText());
            }
        }

        Assert.assertTrue(Validator.validateList(dataTableModel.getRows()));
    }
}
