package com.rodrigotroy.util.printer;

import com.rodrigotroy.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Printer implements IMapUtil,
                                IListUtil {

    public String convertToString(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();

        if (map != null) {

            for (Object o : map.values()) {
                if (o instanceof String) {
                    sb.append("'");
                    sb.append(o);
                    sb.append("'");
                } else if (o instanceof Integer ||
                           o instanceof Double ||
                           o instanceof Short ||
                           o instanceof Long) {
                    sb.append(o);
                } else if (o instanceof ArrayList) {
                    sb.append(this.convertToString(new ArrayList<>((ArrayList<?>) o)));
                }

                sb.append(",");
            }
        }

        return sb.toString();
    }

    @Override
    public List<List<Object>> convertTOList(Map<String, Object> map) {
        return MapUtil.convertTOList(map);
    }

    public String convertToString(List<Object> list,
                                  String separator,
                                  String delimiter1,
                                  String delimiter2) {
        StringBuilder sb = new StringBuilder(delimiter1);

        if (Validator.validateList(list)) {
            for (int x = 0; x < list.size(); x++) {
                Object obj = list.get(x);

                if (obj instanceof ArrayList) {
                    sb.append(this.convertToString(new ArrayList<>((ArrayList<?>) obj),
                                                   separator,
                                                   delimiter1,
                                                   delimiter2));
                } else if (obj != null) {
                    sb.append(String.valueOf(obj).trim());
                }

                if (x < list.size() - 1) {
                    sb.append(separator);
                }
            }

            sb.append(delimiter2);
        }

        return sb.toString();
    }

    public String convertToString(List<Object> list) {
        return this.convertToString(list,
                                    ",",
                                    "[",
                                    "]");
    }

    public String convertToString(Object[] param) {
        StringBuilder sb = new StringBuilder("[");

        if (param != null && param.length > 0) {
            for (Object obj : param) {
                if (obj instanceof ArrayList) {
                    sb.append(this.convertToString(new ArrayList<>((ArrayList<?>) obj)));
                } else if (obj != null) {
                    sb.append(String.valueOf(obj).trim());
                }
            }
        }

        return sb.toString();
    }
}
