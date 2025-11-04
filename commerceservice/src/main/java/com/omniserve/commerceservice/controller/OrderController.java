package com.omniserve.commerceservice.controller;

import com.omniserve.commerceservice.service.OrderProducer;
import com.omniserve.commerceservice.service.OrderService;
import com.omniserve.commondblib.event.OrderRequestEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private OrderService orderService;


    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestEvent orderRequestEvent)
    {
        try{
            return new ResponseEntity<>(orderProducer.sendOrderRequest(orderRequestEvent),HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cancelOrder")
    public ResponseEntity<?> cancelOrderIfPossible(@RequestParam("orderId") String orderId)
    {
        try{
            return orderService.cancelOrderIfPossible(orderId) ? new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
