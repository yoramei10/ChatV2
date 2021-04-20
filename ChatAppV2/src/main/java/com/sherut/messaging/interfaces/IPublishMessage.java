package com.sherut.messaging.interfaces;


import com.sherut.models.ResourceModels.AppMessage;

public interface IPublishMessage {

    public void publish(AppMessage appMessage);
}
