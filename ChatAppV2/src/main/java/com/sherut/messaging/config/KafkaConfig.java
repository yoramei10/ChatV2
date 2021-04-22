package com.sherut.messaging.config;

import com.sherut.messaging.implementations.ListenerMessageService;
import com.sherut.messaging.implementations.PublishMessageService;
import com.sherut.messaging.interfaces.IListenerMessageService;
import com.sherut.messaging.interfaces.IPublishMessageService;
import com.sherut.models.ResourceModels.AppMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.producer.keySerializer}")
    private String keySerializer;
    @Value("${spring.kafka.producer.valueSerializer}")
    private String valueSerializer;

    @Value("${spring.kafka.consumer.keyDeserializer}")
    private String keyDeSerializer;
    @Value("${spring.kafka.consumer.valueDeserializer}")
    private String valueDeserializer;
    @Value("${spring.kafka.consumer.groupId}")
    private String groupID;

    @Bean
    public IPublishMessageService publishMessage(){
        return new PublishMessageService();
    }

    @Bean
    public IListenerMessageService listenerMessage(){
        return new ListenerMessageService();
    }

    @Bean
    public ProducerFactory<String, AppMessage> producerFactory(){

        Map<String, Object> produceConfigMap = new HashMap<>();
        produceConfigMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        produceConfigMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        produceConfigMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return new DefaultKafkaProducerFactory(produceConfigMap);
    }

    @Bean
    public KafkaTemplate<String, AppMessage> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, AppMessage> consumerFactory(){
        Map<String, Object> consumeConfigMap = new HashMap<>();
        consumeConfigMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumeConfigMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        consumeConfigMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeSerializer);
        consumeConfigMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);

        return new DefaultKafkaConsumerFactory<>(consumeConfigMap, new StringDeserializer(), new JsonDeserializer<>(AppMessage.class)) ;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AppMessage> kafkaListenerContainerFactory(){

        ConcurrentKafkaListenerContainerFactory<String, AppMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
        }

}
