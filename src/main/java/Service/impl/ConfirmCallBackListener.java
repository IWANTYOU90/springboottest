package Service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

/**
 * Created by zhouchao on 2019/3/19.
 */
@Service
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {

    private Logger log = LoggerFactory.getLogger(ConfirmCallBackListener.class);
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {


        log.info("收到回调，成功发送到broker");


    }
}
