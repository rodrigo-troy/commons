package com.rodrigotroy.commons.model.datatable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: compass_fecu
 * User: rodrigotroy
 * Date: 9/10/18
 * Time: 18:51
 */
public interface IHeaderLayoutBuilder {
    HeaderLayout createHeaderLayout(List<Object> list);
}
