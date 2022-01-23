package com.rodrigotroy.commons.model.datatable;

import com.rodrigotroy.util.SecureValue;
import com.rodrigotroy.util.Validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 19:36
 */
public class DefaultHeaderBuilder implements IHeaderBuilder {
    private final IColumnVisibilityResolver columnVisibilityResolver;

    public DefaultHeaderBuilder() {
        this.columnVisibilityResolver = new DefaultColumnVisibilityResolver();
    }

    public DefaultHeaderBuilder(IColumnVisibilityResolver columnVisibilityResolver) {
        this.columnVisibilityResolver = columnVisibilityResolver;
    }

    @Override
    public Header createHeader(List<Object> list) {
        Header header = null;

        if (Validator.validateList(list) && list.size() >= 6) {
            DataType dataType = this.resolveDatatype(list.get(1));

            header = new Header(SecureValue.cellRowToString(list,
                                                            0),
                                dataType,
                                Validator.validateNumber(2) ? Integer.parseInt(list.get(2).toString()) : 0,
                                Validator.validateNumber(list.get(3)) && Integer.valueOf(list.get(3).toString()).equals(1),
                                this.columnVisibilityResolver.isVisible(list),
                                this.columnVisibilityResolver.isDetailVisible(list),
                                Validator.validateNumber(list.get(5)) ? Double.parseDouble(list.get(4).toString()) : 1,
                                this.resolveAlignment(dataType),
                                list);
        }

        return header;
    }

    private DataType resolveDatatype(Object rawDataType) {
        if (Validator.validateNumber(rawDataType)) {
            Integer dataTypeNumber = Integer.valueOf(rawDataType.toString());

            for (DataType dataType : DataType.values()) {
                if (dataType.getValue().equals(dataTypeNumber)) {
                    return dataType;
                }
            }
        }

        return DataType.UNKNOWN;
    }

    private ColumnAlignment resolveAlignment(DataType dataType) {
        return switch (dataType) {
            case NUMBER, STRING -> ColumnAlignment.RIGHT;
            case DATE -> ColumnAlignment.CENTER;
            default -> ColumnAlignment.LEFT;
        };

    }
}
