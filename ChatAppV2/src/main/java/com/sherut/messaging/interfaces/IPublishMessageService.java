package com.sherut.messaging.interfaces;


import com.sherut.models.ResourceModels.AppMessage;

public interface IPublishMessageService {

    public void publish(AppMessage appMessage);
}