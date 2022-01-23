package com.rodrigotroy.commons.model.response;


import com.rodrigotroy.commons.model.DefaultListModel;
import com.rodrigotroy.commons.model.IDataTableModel;
import com.rodrigotroy.commons.model.IListModel;
import com.rodrigotroy.commons.model.IMultiListModel;
import com.rodrigotroy.util.Validator;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 29-03-16
 * Time: 15:48
 */
public class DefaultResponse implements IResponse {
    private final IListModel model;

    public DefaultResponse(IListModel model) {
        this.model = model;
    }

    public DefaultResponse(IDataTableModel model) {
        this.model = new DefaultListModel(model.getTitles());
    }

    public DefaultResponse(IMultiListModel model) {
        this.model = new DefaultListModel(model.getDataset(0));
    }

    public String getMessage() {
        if (model != null &&
            Validator.validateList(model.getRows()) &&
            Validator.validateList(model.getRows().get(0)) &&
            Validator.validateString(model.getRows().get(0).get(1))) {
            return model.getRows().get(0).get(1).toString().trim();
        }

        return "";
    }

    @Override
    public String getDetail() {
        return "";
    }

    public Outcome getOutcome() {
        if (model != null && Validator.validateList(model.getRows()) && Validator.validateList(model.getRows().get(0))) {
            if (Validator.isZero(model.getRows().get(0).get(0))) {
                return Outcome.OK;
            } else if (model.getRows().get(0).get(0).equals(2)) {
                return Outcome.WARN;
            }

            return Outcome.ERROR;
        }

        return Outcome.NONE;
    }
}
