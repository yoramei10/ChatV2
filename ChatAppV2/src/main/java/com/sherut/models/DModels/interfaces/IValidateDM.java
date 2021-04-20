package com.sherut.models.DModels.interfaces;

public interface IValidateDM {

    boolean getValue();
    void setValue(boolean value);

    String getValidateMessage();
    void setValidateMessage(String message);
}
