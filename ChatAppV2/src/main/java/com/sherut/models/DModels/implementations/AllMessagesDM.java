package com.sherut.models.DModels.implementations;

import com.sherut.models.DModels.interfaces.IAllMessagesDM;
import com.sherut.models.ResourceModels.AppMessage;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class AllMessagesDM implements IAllMessagesDM {

    @Getter
    List<AppMessage> allMessages;

    @PostConstruct
    public void postConst(){
        allMessages = new ArrayList<>();
    }

    @Override
    public void addMessage(AppMessage appMessage) {
        this.allMessages.add(appMessage);
    }
}
