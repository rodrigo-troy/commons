package com.rodrigotroy.commons.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 4/5/17
 * Time: 18:20
 */
public class SecureValue {
    private SecureValue() {
        throw new IllegalStateException("Utility class");
    }

    public static Boolean objectToBoolean(Object o) {
        if (o != null) {
            if (o.toString().trim().equals("1")) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    public static String objectToString(Object o) {
        if (o != null) {
            return o.toString().trim();
        }

        return "";
    }

    public static Boolean cellRowToBoolean(List<Object> row,
                                           Integer cellIndex) {
        if (Validator.validateList(row) && row.size() > cellIndex && row.get(cellIndex) != null) {
            return SecureValue.objectToBoolean(row.get(cellIndex));
        }

        return Boolean.FALSE;
    }

    public static String cellRowToString(List<Object> row,
                                         Integer cellIndex) {
        return cellRowToString(row,
                               cellIndex,
                               "");
    }

    public static String cellRowToString(List<Object> row,
                                         Integer cellIndex,
                                         String defaultValue) {
        Optional<Object> o = SecureValue.cellRowToObject(row,
                                                         cellIndex);

        return o.map(value -> value.toString().trim()).orElse(defaultValue);
    }

    public static <T> Optional<T> cellRowToObject(List<T> row,
                                                  Integer cellIndex) {
        if (Validator.validateList(row) &&
            row.size() > cellIndex &&
            row.get(cellIndex) != null) {
            return Optional.of(row.get(cellIndex));
        }

        return Optional.empty();
    }

    public static BigDecimal cellRowToBigDecimal(List<Object> row,
                                                 Integer cellIndex) {
        return SecureValue.cellRowToBigDecimal(row,
                                               cellIndex,
                                               BigDecimal.ZERO);
    }

    public static BigDecimal cellRowToBigDecimal(List<Object> row,
                                                 Integer cellIndex,
                                                 BigDecimal defaultValue) {
        final BigDecimal bigDecimal = NumberUtil.cellRowToBigDecimal(row,
                                                                     cellIndex);

        if (bigDecimal != null) {
            return bigDecimal;
        }

        return defaultValue;
    }
}
