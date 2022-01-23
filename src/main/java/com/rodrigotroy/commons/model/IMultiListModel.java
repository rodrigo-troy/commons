package com.rodrigotroy.commons.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 2019-02-15
 * Time: 16:35
 */
public interface IMultiListModel {
    List<List<Object>> getDataset(Integer index);

    void addDataset(List<List<Object>> dataset);
}
