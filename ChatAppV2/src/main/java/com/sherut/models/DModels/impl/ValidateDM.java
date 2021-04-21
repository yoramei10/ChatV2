package com.sherut.models.DModels.impl;

import com.sherut.models.DModels.interfaces.IValidateDM;
import lombok.Getter;
import lombok.Setter;

public class ValidateDM implements IValidateDM {

    @Getter @Setter
    private boolean value;
    @Getter @Setter
    private String message;

    @Override
    public boolean getValue() {
        return value;
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
