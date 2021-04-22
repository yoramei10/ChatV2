package com.sherut.services.applicationServices.implementations;

import com.sherut.models.ResourceModels.TopicParams;
import com.sherut.services.applicationServices.interfaces.IGetTopicApplicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GetTopicApplicationService implements IGetTopicApplicationService {

    @Value("${spring.kafka.topic}")
    String topicName;

    @Value("${spring.kafka.bootstrap-servers}")
    String topicUrl;

    @Override
    public TopicParams getTopicParams() {

        TopicParams topicParams = new TopicParams();
        topicParams.setTopicUrl(topicUrl);
        topicParams.setTopicName(topicName);

        return topicParams;
    }
}
