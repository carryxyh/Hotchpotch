package org.apache.rocketmq.example;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * MessageAmass
 *
 * @author ziyuan
 * @since 2017-11-06
 */
public class MessageAmass {

    public static void main(String[] args) {
        final long flag = 100L; //消息堆积标记
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setNamesrvAddr("");
            consumer.subscribe("", "");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt message : msgs) {
                        long offset = message.getQueueOffset();
                        String maxOffset = message.getProperty(MessageConst.PROPERTY_MAX_OFFSET);
                        long diff = Long.parseLong(maxOffset) - offset;
                        if (diff > flag) {
                            //处理消息堆积情况
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                        //正常消费
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }
}
