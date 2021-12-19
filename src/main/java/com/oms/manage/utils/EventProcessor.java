package com.oms.manage.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.oms.manage.activemq.ActiveMQProducer;
import com.oms.manage.entity.Order;
import com.oms.manage.enums.EventStatus;
import com.oms.manage.enums.OrderStatus;
import com.oms.manage.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventProcessor {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ActiveMQProducer activeMQProducer;

    private static final Logger log = LoggerFactory.getLogger(EventProcessor.class);

    public void processAddEvent(JsonObject eventJson) {
        log.info("[{}] processing ADD event ",eventJson.get("eventId").getAsString());
        Optional<Order> optionalOrder = getOrderFromEventJson(eventJson);
        if (!optionalOrder.isPresent()){
            eventJson.addProperty("eventStatus", EventStatus.FAILED.name());
            eventJson.addProperty("timestamp", System.currentTimeMillis());
            activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
            return;
        }
        Order savedOrder = orderService.addOrder(optionalOrder.get());
        if (savedOrder == null){
            eventJson.addProperty("eventStatus", EventStatus.FAILED.name());
            eventJson.addProperty("timestamp", System.currentTimeMillis());
            activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
            return;
        }
        eventJson.addProperty("orderId",savedOrder.getOrderId());
        eventJson.addProperty("eventStatus", EventStatus.SUCCESS.name());
        eventJson.addProperty("timestamp", System.currentTimeMillis());
        //eventJson.remove("payload");
        activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
    }

    public void processUpdateEvent(JsonObject eventJson) {
        log.info("[{}] processing UPDATE event ",eventJson.get("eventId").getAsString());
        Optional<Order> optionalOrder = getOrderFromEventJson(eventJson);
        if (!optionalOrder.isPresent()){
            eventJson.addProperty("eventStatus", EventStatus.FAILED.name());
            eventJson.addProperty("timestamp", System.currentTimeMillis());
            activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
            return;
        }
        Order savedOrder = orderService.updateOrder(optionalOrder.get(),optionalOrder.get().getOrderId());
        if (savedOrder == null){
            eventJson.addProperty("eventStatus", EventStatus.FAILED.name());
            eventJson.addProperty("timestamp", System.currentTimeMillis());
            activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
            return;
        }
        eventJson.addProperty("orderId",savedOrder.getOrderId());
        eventJson.addProperty("eventStatus", EventStatus.SUCCESS.name());
        eventJson.addProperty("timestamp", System.currentTimeMillis());
        //eventJson.remove("payload");
        activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
    }

    public void processDeleteEvent(JsonObject eventJson) {
        log.info("[{}] processing DELETE event ",eventJson.get("eventId").getAsString());
        Optional<Order> optionalOrder = getOrderFromEventJson(eventJson);
        if (!optionalOrder.isPresent()){
            eventJson.addProperty("eventStatus", EventStatus.FAILED.name());
            eventJson.addProperty("timestamp", System.currentTimeMillis());
            activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
            return;
        }
        boolean result = orderService.deleteOrder(optionalOrder.get().getOrderId());
        if (! result){
            eventJson.addProperty("eventStatus", EventStatus.FAILED.name());
            eventJson.addProperty("timestamp", System.currentTimeMillis());
            activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
            return;
        }
        eventJson.addProperty("orderId",optionalOrder.get().getOrderId());
        eventJson.addProperty("eventStatus", EventStatus.SUCCESS.name());
        eventJson.addProperty("timestamp", System.currentTimeMillis());
        //eventJson.remove("payload");
        activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
    }

    public void processCancelEvent(JsonObject eventJson) {
        log.info("[{}] processing CANCEL event ",eventJson.get("eventId").getAsString());
        Optional<Order> optionalOrder = getOrderFromEventJson(eventJson);
        if (!optionalOrder.isPresent()){
            eventJson.addProperty("eventStatus", EventStatus.FAILED.name());
            eventJson.addProperty("timestamp", System.currentTimeMillis());
            activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
            return;
        }
        Order order = optionalOrder.get();
        order.setOrderStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderService.updateOrder(order,order.getOrderId());
        if (savedOrder == null){
            eventJson.addProperty("eventStatus", EventStatus.FAILED.name());
            eventJson.addProperty("timestamp", System.currentTimeMillis());
            activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
            return;
        }
        eventJson.addProperty("orderId",savedOrder.getOrderId());
        eventJson.addProperty("eventStatus", EventStatus.SUCCESS.name());
        eventJson.addProperty("timestamp", System.currentTimeMillis());
        //eventJson.remove("payload");
        activeMQProducer.sendMessageToOMSPublisherTopic(eventJson.toString());
    }

    private Optional<Order> getOrderFromEventJson(JsonObject eventJson){
        JsonObject object = eventJson.get("payload").getAsJsonObject();
        ObjectMapper mapper = new ObjectMapper();
        Order order = null;
        try {
            order = mapper.readValue(object.toString(),Order.class);
        } catch (JsonProcessingException e) {
            log.error("Json parsing exception : "+e.getMessage());
        }
        if (order == null)
            return Optional.empty();
        else
            return Optional.of(order);

    }
}
