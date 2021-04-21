package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.ResourceModels.TopicParams;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

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
