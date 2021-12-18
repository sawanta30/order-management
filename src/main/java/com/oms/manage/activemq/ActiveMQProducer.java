package com.oms.manage.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class ActiveMQProducer {

    private static final Logger log = LoggerFactory.getLogger(ActiveMQProducer.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Topic osmPublisherTopic;


    public void sendMessageToOMSPublisherTopic(String message){
        log.info("Received request to write message in topic : "+message);
        jmsTemplate.convertAndSend(osmPublisherTopic,message);
    }
}
