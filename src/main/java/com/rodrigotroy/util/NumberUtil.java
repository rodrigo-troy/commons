package com.rodrigotroy.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 2019-05-24
 * Time: 11:03
 */
public class NumberUtil {
    private NumberUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getValueAsString(Object value,
                                          Boolean stripTrailingZeros,
                                          Locale locale,
                                          String defaultValue) {
        String val = NumberUtil.getValueAsString(value,
                                                 stripTrailingZeros,
                                                 locale);

        return Validator.validateString(val) ? val : defaultValue;
    }

    public static String getValueAsString(Object value,
                                          Boolean stripTrailingZeros,
                                          Boolean grouping,
                                          Locale locale) {
        return NumberUtil.getValueAsString(value,
                                           stripTrailingZeros,
                                           grouping,
                                           null,
                                           locale);
    }

    public static String getValueAsString(Object value,
                                          Boolean stripTrailingZeros,
                                          Boolean grouping,
                                          Integer fractionDigits,
                                          Locale locale) {
        return NumberUtil.getValueAsString(value,
                                           stripTrailingZeros,
                                           grouping,
                                           fractionDigits,
                                           fractionDigits,
                                           locale);
    }

    public static String getValueAsString(Object value,
                                          Boolean stripTrailingZeros,
                                          Boolean grouping,
                                          Integer minimumFractionDigits,
                                          Integer maximumFractionDigits,
                                          Locale locale) {
        if (Validator.validateString(value)) {
            if (!value.toString().contains(".") && StringUtils.countMatches(value.toString(),
                                                                            ",") == 1) {
                value = value.toString().replace(",",
                                                 ".");
            } else if (!value.toString().contains(".") && StringUtils.countMatches(value.toString(),
                                                                                   ",") > 1) {
                value = value.toString().replace(",",
                                                 "");
            } else if (value.toString().contains(".") && StringUtils.countMatches(value.toString(),
                                                                                  ".") == 1 && StringUtils.countMatches(value.toString(),
                                                                                                                        ",") > 1) {
                value = value.toString().replace(",",
                                                 "");
            } else if (!value.toString().contains(",") && StringUtils.countMatches(value.toString(),
                                                                                   ".") > 1) {
                value = value.toString().replace(".",
                                                 "");
            } else if (StringUtils.countMatches(value.toString(),
                                                ".") == 1 && StringUtils.countMatches(value.toString(),
                                                                                      ",") == 1) {
                value = value.toString().replace(".",
                                                 "");
                value = value.toString().replace(",",
                                                 ".");
            }
        }

        if (Validator.validateNumber(value)) {
            BigDecimal bigDecimal = new BigDecimal(value.toString());

            if (stripTrailingZeros) {
                bigDecimal = bigDecimal.stripTrailingZeros();
            }

            int decimals = bigDecimal.scale();

            NumberFormat nf = NumberFormat.getInstance(locale);

            if (minimumFractionDigits == null || maximumFractionDigits == null) {
                nf.setMinimumFractionDigits(decimals);
                nf.setMaximumFractionDigits(decimals);
            } else {
                nf.setMinimumFractionDigits(minimumFractionDigits);
                nf.setMaximumFractionDigits(maximumFractionDigits);
            }

            nf.setGroupingUsed(grouping);

            return nf.format(bigDecimal);
        }

        return "";
    }

    public static String getValueAsString(Object value,
                                          Boolean stripTrailingZeros,
                                          Locale locale) {
        return NumberUtil.getValueAsString(value,
                                           stripTrailingZeros,
                                           false,
                                           locale);
    }

    public static BigDecimal cellRowToBigDecimal(List<Object> row,
                                                 Integer cellIndex) {
        return NumberUtil.cellRowToBigDecimal(row,
                                              cellIndex,
                                              true,
                                              false,
                                              4,
                                              4,
                                              Locale.US);
    }

    public static BigDecimal cellRowToBigDecimal(List<Object> row,
                                                 Integer cellIndex,
                                                 Boolean stripTrailingZeros,
                                                 Boolean grouping,
                                                 Integer minimumFractionDigits,
                                                 Integer maximumFractionDigits,
                                                 Locale locale) {
        String valueAsString = SecureValue.cellRowToString(row,
                                                           cellIndex);
        return Validator.validateNumber(valueAsString,
                                        true) ? new BigDecimal(NumberUtil.getValueAsString(valueAsString,
                                                                                           stripTrailingZeros,
                                                                                           grouping,
                                                                                           minimumFractionDigits,
                                                                                           maximumFractionDigits,
                                                                                           locale)) : null;
    }
}
