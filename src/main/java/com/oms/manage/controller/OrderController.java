package com.oms.manage.controller;

import com.oms.manage.entity.Order;
import com.oms.manage.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @RequestMapping(value = "/{orderId}",method = RequestMethod.PUT)
    public Order updateOrder(@RequestBody Order order,@PathVariable long orderId){
        return orderService.updateOrder(order);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    @RequestMapping(value = "/{orderId}",method = RequestMethod.GET)
    public Order getOrder(@PathVariable long orderId){
        return orderService.getOrder(orderId);
    }

    @RequestMapping(value = "/{orderId}",method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable long orderId){
        boolean result = orderService.deleteOrder(orderId);
        if (result)
            return "order deleted successfully : "+orderId;
        else
            return "order deletion un-successful : "+orderId;
    }


}
