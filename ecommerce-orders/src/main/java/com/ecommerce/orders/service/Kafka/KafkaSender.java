package com.ecommerce.orders.service.Kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import com.ecommerce.orders.domain.Order;
import com.ecommerce.orders.domain.UpdateStatusByID;

@Service
public class KafkaSender {
	private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Value("${kafka.creation.topic.name}")
    private String creationTopicName;

    @Value("${kafka.update.topic.name}")
    private String updateTopicName;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDataForOrdersCreation(List<Order> orders) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, creationTopicName);
        kafkaTemplate.send(new GenericMessage<List<Order>>(orders, headers));
        logger.info("Data Sent to Kafka for Creation : {}  Topic : {}", orders.toString(), creationTopicName);
    }

    public void sendDataForOrdersUpdate(UpdateStatusByID updateStatusByID) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, updateTopicName);
        kafkaTemplate.send(new GenericMessage<UpdateStatusByID>(updateStatusByID, headers));
        logger.info("Data Sent to Kafka for Updation : {}  Topic : {}", updateStatusByID.toString(), updateTopicName);
    }

}
