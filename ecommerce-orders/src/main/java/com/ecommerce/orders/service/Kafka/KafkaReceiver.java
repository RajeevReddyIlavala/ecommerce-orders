package com.ecommerce.orders.service.Kafka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ecommerce.orders.domain.Order;
import com.ecommerce.orders.domain.UpdateStatusByID;
import com.ecommerce.orders.service.OrderService;

@Service
public class KafkaReceiver {
	private static final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
	private OrderService ordersService;
	/*
	 * @Autowired public KafkaReceiver(OrderService ordersService) {
	 * this.ordersService = ordersService; }
	 */

    @KafkaListener(topics = "${kafka.creation.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveDataForOrdersCreation(List<Order> orders) {
        logger.info("Data Received For Creation : {}", orders.toString());
        if (!CollectionUtils.isEmpty(orders)) {
            orders.forEach(order -> {
                try {
                    ordersService.createOrder(order);
                } catch (Exception e) {
                    logger.error("Error While Saving Order : {}", order.toString(),e);
                }
            });
        } else {
            logger.warn("Received Empty List");
        }
    }

    @KafkaListener(topics = "${kafka.update.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveDataForOrdersUpdate(UpdateStatusByID updateStatusByID) {
        logger.info("Data Received For Status Update: {}", updateStatusByID.toString());
        if (updateStatusByID != null && !CollectionUtils.isEmpty(updateStatusByID.getOrderIDs())
            && updateStatusByID.getStatus() != null) {
            updateStatusByID.getOrderIDs().forEach(orderID -> {
                try {
                	ordersService.updateOrderByStatus(orderID, updateStatusByID.getStatus());
                } catch (Exception e) {
                    logger.error("Error While Updating Order : {}", orderID,e);
                }
            });
        } else {
            logger.warn("Received Empty List");
        }
    }
}
