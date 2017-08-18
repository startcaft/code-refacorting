package com.basic.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * JMS消息生产者服务，用于发送消息
 */
@Component
class MQProducerService {

    private static final Logger LOGGER = Logger.getLogger(MQProducerService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public final void sendMessage(final Destination destination, final String message){
        {
            jmsTemplate.send(destination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(message);
                }
            });

            LOGGER.debug("生产者，发送了一条消息：" + message);
        }
    }
}
