package com.mq.redis.config;

import com.basic.core.entity.contants.GlobalConstants;
import com.mq.redis.listener.DicTypeQueueMessageListener;
import com.mq.redis.storage.DicTypeStorage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
@Import(value = {RedisConfig.class})
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

    // ActiveMQQueue 指定队列名称的Destination，消息的来源
    @Bean(name = GlobalConstants.DICTYPE_QUEUE_BEAN_NAME)
    public ActiveMQQueue activeMQQueue(){
        {
            ActiveMQQueue queue = new ActiveMQQueue(GlobalConstants.QUEUE_NAME_DICTYPE);
            return queue;
        }
    }

    // 自定义的MessageListener消息监听器
    @Bean
    public DicTypeQueueMessageListener dicTypeQueueMessageListener(@Autowired DicTypeStorage dicTypeStorage){
        {
            DicTypeQueueMessageListener messageListener = new DicTypeQueueMessageListener(dicTypeStorage);
            return messageListener;
        }
    }

    /** Spring提供的MessageListenerContainer 负责接收消息并分发到MessageListener **/
    @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(
            @Autowired DicTypeQueueMessageListener dicTypeQueueMessageListener,
            @Qualifier(GlobalConstants.DICTYPE_QUEUE_BEAN_NAME) ActiveMQQueue destination,
            @Qualifier("connectionFactory") SingleConnectionFactory connectionFactory){
        {
            DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
            defaultMessageListenerContainer.setMessageListener(dicTypeQueueMessageListener);
            defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
            defaultMessageListenerContainer.setDestination(destination);
            return defaultMessageListenerContainer;
        }
    }
}