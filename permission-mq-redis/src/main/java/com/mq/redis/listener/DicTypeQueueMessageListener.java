package com.mq.redis.listener;


import com.alibaba.fastjson.JSON;
import com.basic.core.entity.vo.DicTypeVo;
import com.mq.redis.storage.DicTypeStorage;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * dicTypeQueue 消息队列监听器
 */
public class DicTypeQueueMessageListener implements MessageListener {

    private DicTypeStorage dicTypeStorage;

    public DicTypeQueueMessageListener(DicTypeStorage dicTypeStorage) {
        this.dicTypeStorage = dicTypeStorage;
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            String jsonStr = msg.getText();

            if (jsonStr != null || jsonStr.equals("")){
                DicTypeVo vo = JSON.parseObject(jsonStr,DicTypeVo.class);

                dicTypeStorage.addOrUpdate(vo);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
