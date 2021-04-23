package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.models.ResourceDM.TopicParams;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class GetTopicTest extends BaseTest{

    @Autowired
    private RestControllerApp restControllerApp;

    @Test
    public void getTopic_Success(){

        ResponseEntity<TopicParams> topic = restControllerApp.getTopic();

        Assert.assertEquals(TOPIC_NAME,topic.getBody().getTopicName());
        Assert.assertTrue(topic.getBody().getTopicUrl().contains("localhost"));
    }

}
