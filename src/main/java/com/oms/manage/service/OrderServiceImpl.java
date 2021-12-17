package com.oms.manage.service;

import com.oms.manage.dao.OrderRepository;
import com.oms.manage.entity.Order;
import com.oms.manage.exception.OSMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    private static Supplier<OSMException> osmExceptionSupplier = new Supplier<OSMException>() {
        @Override
        public OSMException get() {
            return new OSMException(32500,400,"Order not found");
        }
    };

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
        return orderRepository.findById(orderId).orElseThrow(osmExceptionSupplier);
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
    public Order updateOrder(Order order, long orderId) {
        Order dbOrder = orderRepository.findById(orderId).orElseThrow(osmExceptionSupplier);
        dbOrder.setActive(order.getActive());
        dbOrder.setOrderDate(order.getOrderDate());
        dbOrder.setOrderAddress(order.getOrderAddress());
        dbOrder.setOrderDetails(order.getOrderDetails());
        dbOrder.setAddressType(order.getAddressType());
        dbOrder.setBillingStatus(order.getBillingStatus());
        dbOrder.setDeliveredDate(order.getDeliveredDate());
        dbOrder.setOrderStatus(order.getOrderStatus());
        dbOrder.setOrderType(order.getOrderType());
        dbOrder.setExpDeliveryDate(order.getExpDeliveryDate());
        dbOrder.setPrepaid(order.getPrepaid());
        return orderRepository.save(dbOrder);
    }
}
