package com.sherut.messaging.interfaces;

import com.sherut.models.ResourceDM.AppMessage;

public interface IListenerMessageService {

    void consume(AppMessage message);
}
