package com.oms.manage.activemq;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oms.manage.enums.Event;
import com.oms.manage.service.OrderService;
import com.oms.manage.utils.EventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:osm-dev-config.properties")
public class ActiveMQConsumer {

    private static final Logger log = LoggerFactory.getLogger(ActiveMQConsumer.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private ActiveMQProducer activeMQProducer;
    @Autowired
    private EventProcessor eventProcessor;

    @JmsListener(destination = "${osm.activemq.queue}")
    public void consumeMessage(String message) {
        log.info("Received message from Queue : " + message);
        JsonObject eventJson = JsonParser.parseString(message).getAsJsonObject();
        if (eventJson.has("event") && eventJson.has("eventId") && eventJson.has("payload")) {
            Event OSM_Event = Event.valueOf(eventJson.get("event").getAsString().toUpperCase());
            switch (OSM_Event) {
                case ADD: {
                    eventProcessor.processAddEvent(eventJson);
                    break;
                }
                case UPDATE: {
                    eventProcessor.processUpdateEvent(eventJson);
                    break;
                }
                case CANCEL: {
                    eventProcessor.processCancelEvent(eventJson);
                    break;
                }
                case DELETE: {
                    eventProcessor.processDeleteEvent(eventJson);
                    break;
                }
                default: {
                log.info("Invalid event recieved : {}",OSM_Event.name());
                }

            }
        }

    }


}
