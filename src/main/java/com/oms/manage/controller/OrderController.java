package com.oms.manage.controller;

import com.oms.manage.entity.Order;
import com.oms.manage.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Order addOrder(@RequestBody Order order) {
        log.info("Received request for adding order");
        return orderService.addOrder(order);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PUT)
    public Order updateOrder(@RequestBody Order order, @PathVariable long orderId) {
        log.info("Received request for updating order");
        return orderService.updateOrder(order, orderId);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        log.info("Received request for fetching all orders");
        return orderService.getAllOrders();
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable long orderId) {
        log.info("Received request for getting order -> orderId : "+orderId);
        return orderService.getOrder(orderId);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable long orderId) {
        log.info("Received request for deleting order");
        boolean result = orderService.deleteOrder(orderId);
        if (result)
            return "order deleted successfully : " + orderId;
        else
            return "order deletion un-successful : " + orderId;
    }


}
