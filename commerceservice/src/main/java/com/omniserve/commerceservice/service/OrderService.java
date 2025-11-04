package com.omniserve.commerceservice.service;

import com.omniserve.commondblib.entity.OrderRequest;
import com.omniserve.commondblib.event.OrderRequestEvent;
import com.omniserve.commondblib.repository.OrderRequestRepository;
import com.omniserve.commondblib.state.OrderState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrderService {
    @Autowired
    private OrderRequestRepository orderRequestRepository;

    public OrderRequestEvent getOrderRequestById(String orderId) throws ResponseStatusException,Exception
    {
        OrderRequest orderRequest = orderRequestRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Exist"));

        return new OrderRequestEvent(
                orderRequest.getOrderId(),
                orderRequest.getUserId(),
                orderRequest.getItemId(),
                orderRequest.getOrderQuantity(),
                orderRequest.getOrderState(),
                orderRequest.getTimestamp()
        );
    }

    public Boolean cancelOrderIfPossible(String orderId)
    {
        return orderRequestRepository.processOrder(orderId, OrderState.PENDING.name(),OrderState.CANCELLED.name()).isPresent();
    }
}
