package com.sherut.models.DModels.impl;

import com.sherut.models.DModels.interfaces.IValidateDM;

public class ValidateDM implements IValidateDM {

    private boolean value;
    private String message;

    @Override
    public boolean getValue() {
        return value;
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String getValidateMessage() {
        return message;
    }

    @Override
    public void setValidateMessage(String message) {
        this.message = message;
    }
}
