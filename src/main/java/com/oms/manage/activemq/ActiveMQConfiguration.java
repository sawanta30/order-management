package com.oms.manage.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@EnableJms
@PropertySource("osm-dev-config.properties")
public class ActiveMQConfiguration {


    @Value("${osm.activemq.broker-url}")
    private String avtiveMQBrokerURL;
    @Value("${osm.activemq.queue}")
    private String OSMQueueNameStr;
    @Value("${osm.activemq.topic}")
    private String OSMEventPublisherTopic;
    @Value("${osm.activemq.username}")
    private String activeMQUserName;
    @Value("${osm.activemq.password}")
    private String activeMQPassword;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() throws JMSException {
       ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
       connectionFactory.createConnection(activeMQUserName, activeMQPassword);
       connectionFactory.setBrokerURL(avtiveMQBrokerURL);
       return connectionFactory;
    }

    @Bean
    public Topic osmPublisherTopic(){
        return new ActiveMQTopic(OSMEventPublisherTopic);
    }

    @Bean
    public Queue osmConsumerQueue(){
        return new ActiveMQQueue(OSMQueueNameStr);
    }

    public JmsTemplate jmsTemplate() throws JMSException {
        return new JmsTemplate(activeMQConnectionFactory());
    }
}
