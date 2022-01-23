package com.rodrigotroy.commons.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DefaultComboBoxModel implements IComboBoxModel,
                                             Serializable {
    private Object selectedOption;
    private List<List<Object>> options;

    @Override
    public List<List<Object>> getOptions() {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }

        return this.options;
    }

    @Override
    public void setOptions(List<List<Object>> options) {
        this.options = options;
    }

    @Override
    public Object getSelectedOption() {
        return this.selectedOption;
    }

    @Override
    public void setSelectedOption(Object option) {
        this.selectedOption = option;
    }

    @Override
    public IComboBoxModel clone() {
        DefaultComboBoxModel clone = new DefaultComboBoxModel();
        clone.setOptions(new ArrayList<>(this.getOptions()));
        clone.setSelectedOption(this.getSelectedOption());

        return clone;
    }
}
