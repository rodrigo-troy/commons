package com.rodrigotroy.commons.util;

import com.rodrigotroy.commons.model.IDataTableModel;
import com.rodrigotroy.commons.model.IListModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {
    private Validator() {
        throw new IllegalStateException("Utility class");
    }

    public static Boolean validateMail(String mail) {
        String emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern emailCompiledPattern = Pattern.compile(emailPattern);
        Matcher matcher = emailCompiledPattern.matcher(mail);

        return matcher.matches();
    }

    @Deprecated
    public static boolean valorEsCero(Object x) {
        return Validator.isZero(x);
    }

    public static boolean isZero(Object x) {
        if (x != null) {
            if (x instanceof String) {
                final boolean isDigits = NumberUtils.isDigits(x.toString());

                if (isDigits) {
                    return Validator.isZero(NumberUtils.toDouble(x.toString()));
                }
            } else {
                boolean valorEsCero = x.equals(0.0) || x.equals(0) || x.equals(0L) || x.equals(0F) || x.equals(new BigDecimal(0));

                if (x instanceof BigDecimal valor) {
                    valorEsCero = valorEsCero || (valor.floatValue() == 0);
                }

                return valorEsCero;
            }
        }

        return false;
    }

    public static Boolean validateDate(Object o) {
        if (o != null) {
            if (o instanceof java.sql.Date) {
                return true;
            } else {
                return o instanceof java.util.Date;
            }
        }

        return false;
    }

    public static Boolean validateList(Object o) {
        if (o != null) {
            if (o instanceof List) {
                return !((List<?>) o).isEmpty();
            }
        }

        return false;
    }

    public static Boolean isNotEmpty(IListModel o) {
        if (o != null) {
            return Validator.validateList(o.getRows());
        }

        return false;
    }

    public static Boolean validateList(Object o,
                                       Integer size) {
        if (o != null) {
            if (o instanceof List) {
                if (!((List<?>) o).isEmpty()) {
                    return ((List<?>) o).size() == size;
                }
            }
        }

        return false;
    }

    public static Boolean validateList(String value,
                                       Map<?, ?> map) {
        if (map != null && value != null) {
            return Validator.validateList(map.get(value));
        }

        return false;
    }

    public static Boolean validateString(Object o) {
        return o instanceof String && StringUtils.isNotEmpty((String) o);
    }

    public static Boolean validateString(Object o,
                                         Integer size) {
        return Validator.validateString(o) && ((String) o).length() == size;
    }

    public static Boolean validateString(Object o,
                                         String txt) {
        return Validator.validateString(o) && o.equals(txt);
    }

    public static Boolean validateInteger(Object o) {
        if (o != null) {
            return o instanceof Integer;
        }

        return false;
    }

    public static Boolean validateInteger(Object o,
                                          Integer x) {
        return Validator.validateInteger(o) && o.equals(x);
    }

    public static Boolean validateNumber(Object o) {
        return Validator.validateNumber(o,
                                        false);
    }

    public static Boolean validateCellNumber(List<Object> row,
                                             Integer cellIndex,
                                             Boolean allowEditionOnValidation) {
        Optional<Object> object = SecureValue.cellRowToObject(row,
                                                              cellIndex);

        return object.isPresent() && Validator.validateNumber(object.get(),
                                                              allowEditionOnValidation);
    }

    public static Boolean validateCellNumber(List<Object> row,
                                             Integer cellIndex) {
        return Validator.validateCellNumber(row,
                                            cellIndex,
                                            false);
    }

    public static Boolean validateNumber(Object o,
                                         Boolean allowEditionOnValidation) {
        if (o != null) {
            if (o instanceof Number) {
                return true;
            } else if (o instanceof String) {
                if (allowEditionOnValidation) {
                    String value = SecureValue.objectToString(o);

                    if (value.startsWith("-") || value.startsWith("+")) {
                        int sumOperatorCount = 0;
                        int restOperatorCount = 0;

                        for (char c : value.toCharArray()) {
                            if (c == '-') {
                                restOperatorCount++;
                            } else if (c == '+') {
                                sumOperatorCount++;
                            }

                            if (restOperatorCount > 1 || sumOperatorCount > 1) {
                                return false;
                            }
                        }

                        if (sumOperatorCount == 1) {
                            value = value.replace("+",
                                                  "");
                        } else if (restOperatorCount == 1) {
                            value = value.replace("-",
                                                  "");
                        }
                    }

                    String editedValue = NumberUtil.getValueAsString(value,
                                                                     true,
                                                                     Locale.US);


                    return NumberUtils.isCreatable(editedValue) || NumberUtils.isParsable(editedValue);
                }

                return NumberUtils.isCreatable(o.toString().trim()) || NumberUtils.isParsable(o.toString().trim());
            }
        }

        return false;
    }


    public static Boolean validateDouble(Object o) {
        if (o != null) {
            return o instanceof Double;
        }

        return false;
    }

    public static Boolean validateBigDecimal(Object o) {
        if (o != null) {
            return o instanceof BigDecimal;
        }

        return false;
    }

    public static Boolean validateLong(Object o) {
        if (o != null) {
            return o instanceof Long;
        }

        return false;
    }

    public static Boolean validateLong(Object o,
                                       Integer x) {
        if (o != null) {
            if (o instanceof Long) {
                return o.equals(Long.valueOf(x));
            }
        }

        return false;
    }

    public static Boolean validateDataTableModel(IDataTableModel model) {
        if (model != null) {
            return Validator.validateList(model.getHeaders()) && Validator.validateList(model.getRows());
        }

        return false;
    }

    public static Boolean validateRUT(String rut) {
        if (Validator.validateString(rut)) {
            return Pattern.matches("^0*(\\d{1,3}(\\.?\\d{3})*)-([\\dkK])$",
                                   rut);
        }

        return false;
    }

    @Deprecated
    public static Boolean validateIListModel(IListModel model) {
        return model != null && Validator.validateList(model.getRows());
    }
}
