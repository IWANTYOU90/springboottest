package Service.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by zhouchao on 2019/3/19.
 */
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("收到会掉");

        System.out.println("return-message="+new String(message.getBody())+ ",replyCode:" + i + ",replyText:" + s + ",exchange:" + s1 + ",routingKey:" + s2);
    }
}
