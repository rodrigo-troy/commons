package com.rodrigotroy.commons.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: compass_fecu
 * User: rodrigotroy
 * Date: 9/10/18
 * Time: 18:44
 */
public class DefaultComplexHeaderDataTableModel extends DefaultDataTableModel implements IComplexHeaderDataTableModel {
    private List<List<Object>> headerLayout;

    @Override
    public List<List<Object>> getHeaderLayout() {
        if (headerLayout == null) {
            headerLayout = new ArrayList<>();
        }

        return headerLayout;
    }

    @Override
    public void setHeaderLayout(List<List<Object>> headerLayout) {
        this.headerLayout = headerLayout;
    }
}
