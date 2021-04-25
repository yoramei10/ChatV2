package com.sherut.messaging.interfaces;


import com.sherut.models.ResourceDM.AppMessage;

public interface IPublishMessageGWService {

    public void publish(AppMessage appMessage);
}
