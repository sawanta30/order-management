package com.oms.manage.service;

import com.oms.manage.dao.OrderRepository;
import com.oms.manage.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean deleteOrder(long orderId) {
        boolean result = true;
        try {
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }
}
