package com.basic.core.config.jms;

import com.basic.core.util.GlobalConstants;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQConfig {

    // ActiveMQ提供的真正的ConnectionFactory
    @Bean(name="targetConnectionFactory")
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setBrokerURL(GlobalConstants.JMS_BROKER_URL);
            connectionFactory.setUserName(GlobalConstants.JMS_USERNAME);
            connectionFactory.setPassword(GlobalConstants.JMS_PASSWORD);
            return connectionFactory;
        }
    }

    // ActiveMQ提供一个PooledConnectionFactory，用于将Session，Connection和MessageProducer池化
    @Bean(name="pooledConnectionFactory")
    public PooledConnectionFactory pooledConnectionFactory(
                                        @Qualifier("targetConnectionFactory") ActiveMQConnectionFactory targetConnectionFactory){
        {
            PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
            pooledConnectionFactory.setConnectionFactory(targetConnectionFactory);
            return pooledConnectionFactory;
        }
    }

    /** Spring提供的ConnectionFactory用来管理JMS厂商的ConnectionFactory **/
    @Bean(name="connectionFactory")
    public SingleConnectionFactory singleConnectionFactory(
                                        @Qualifier("pooledConnectionFactory") PooledConnectionFactory targetConnectionFactory){
        {
            SingleConnectionFactory connectionFactory = new SingleConnectionFactory();
            connectionFactory.setTargetConnectionFactory(targetConnectionFactory);
            return connectionFactory;
        }
    }

    // ActiveMQQueue 指定队列名称的Destination，消息的目的地
    @Bean(name = GlobalConstants.DICTYPE_QUEUE_BEAN_NAME)
    public ActiveMQQueue activeMQQueue(){
        {
            ActiveMQQueue queue = new ActiveMQQueue(GlobalConstants.QUEUE_NAME_DICTYPE);
            return queue;
        }
    }

    /** Spring提供的JmsTemplate用来消费消息 **/
    @Bean
    public JmsTemplate jmsTemplate(
                                    @Qualifier("connectionFactory") SingleConnectionFactory connectionFactory){
        {
            JmsTemplate jmsTemplate = new JmsTemplate();
            jmsTemplate.setConnectionFactory(connectionFactory);
            return jmsTemplate;
        }
    }
}