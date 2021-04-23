package com.sherut.models.DM.interfaces;

import com.sherut.models.ResourceDM.AppMessage;

import java.util.List;

public interface IAllMessagesDM {

    List<AppMessage> getAllMessages();

    void addMessage(AppMessage appMessage);
}
