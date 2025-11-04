package com.omniserve.commerceservice.service;

import com.omniserve.commondblib.entity.OrderRequest;
import com.omniserve.commondblib.event.OrderRequestEvent;
import com.omniserve.commondblib.repository.OrderRequestRepository;
import com.omniserve.commondblib.state.OrderState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, OrderRequestEvent> kafkaTemplate;

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    public final String TOPIC = "order_request";

    @Transactional
    public OrderRequestEvent sendOrderRequest(OrderRequestEvent orderRequestEvent)
    {
        String userId = "user123";
        OrderRequest orderRequest = OrderRequest.builder()
                .userId(userId)
                .itemId(orderRequestEvent.getItemId())
                .orderQuantity(orderRequestEvent.getOrderQuantity())
                .orderState(OrderState.PENDING)
                .build();
        orderRequestRepository.save(orderRequest);

        orderRequestEvent.setOrderId(orderRequest.getOrderId());
        orderRequestEvent.setOrderState(OrderState.PENDING);
        orderRequestEvent.setTimestamp(LocalDateTime.now());
        orderRequestEvent.setUserId(userId);

        kafkaTemplate.send(TOPIC,orderRequestEvent.getOrderId(),orderRequestEvent);
        return orderRequestEvent;
    }
}
