package com.oms.manage.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
@PropertySource("osm-dev-config.properties")
public class ActiveMQConsumer {

    private static final Logger log = LoggerFactory.getLogger(ActiveMQConsumer.class);

    @JmsListener(destination = "${osm.activemq.queue}")
    public void consumeMessage(String message){
       log.info("Received message from Queue : "+message);
    }
}
