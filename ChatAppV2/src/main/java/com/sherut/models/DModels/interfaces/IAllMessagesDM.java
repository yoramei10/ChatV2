package com.sherut.models.DModels.interfaces;

import com.sherut.models.ResourceModels.AppMessage;

import java.util.List;

public interface IAllMessagesDM {

    List<AppMessage> getAllMessages();

    void addMessage(AppMessage appMessage);
}
