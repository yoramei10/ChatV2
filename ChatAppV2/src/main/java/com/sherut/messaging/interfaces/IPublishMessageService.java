package com.sherut.messaging.interfaces;


import com.sherut.models.ResourceDM.AppMessage;

public interface IPublishMessageService {

    public void publish(AppMessage appMessage);
}
