package com.oms.manage.service;

import com.oms.manage.dao.OrderRepository;
import com.oms.manage.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order addOrder(Order order);
    List<Order> getAllOrders();
    Order getOrder(long orderId);
    boolean deleteOrder(long orderId);
    Order updateOrder(Order order);

}
