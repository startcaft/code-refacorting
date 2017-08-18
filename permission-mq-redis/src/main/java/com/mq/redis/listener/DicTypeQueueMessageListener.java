package com.mq.redis.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * dicTypeQueue 消息队列监听器
 */
public class DicTypeQueueMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
