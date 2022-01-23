package com.rodrigotroy.util.printer;

import java.util.List;
import java.util.Map;

public interface IMapUtil {

    String convertToString(Map<String, Object> mapa) throws
                                                     RuntimeException;

    List<List<Object>> convertTOList(Map<String, Object> mapa) throws
                                                               RuntimeException;
}
