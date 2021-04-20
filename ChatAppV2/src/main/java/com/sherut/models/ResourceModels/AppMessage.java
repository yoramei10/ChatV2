package com.sherut.models.ResourceModels;


import java.io.Serializable;

public class AppMessage implements Serializable {

    String id;
    String name;
    Object msgContext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

        public void setName(String name) {
        this.name = name;
    }

    public Object getMsgContext() {
        return msgContext;
    }

    public void setMsgContext(Object msgContext) {
        this.msgContext = msgContext;
    }


}
