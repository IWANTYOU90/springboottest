package Service.impl;

import Service.MqService;
import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhouchao on 2019/3/17.
 */
@Service
public class MqServiceImpl implements MqService {

    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String[] LOG_LEVEL_ARR = {"debug", "info", "error"};

    @Override
    public void receiveMessage() {

        try {
            ConnectionFactory factory = new ConnectionFactory();

            factory.setHost("127.0.0.1");

            Connection connection = factory.newConnection();

            Channel channel = connection.createChannel();

            //指定一个交换器
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            //设置日志级别
            int rand = new Random().nextInt(3);

            //创建三个非持久的，唯一的，自动删除的队列，分别接受不同的队列，分别接受不同的日志
            String debugQueueName = channel.queueDeclare().getQueue();
            String infoQueueName = channel.queueDeclare().getQueue();
            String errorQueName = channel.queueDeclare().getQueue();

            //绑定交换器和队列
            channel.queueBind(LOG_LEVEL_ARR[0],EXCHANGE_NAME,LOG_LEVEL_ARR[0]);
            channel.queueBind(LOG_LEVEL_ARR[1],EXCHANGE_NAME,LOG_LEVEL_ARR[1]);
            channel.queueBind(LOG_LEVEL_ARR[2],EXCHANGE_NAME,LOG_LEVEL_ARR[2]);

            while (true) {
                //消费消息

                //System.out.println("2222222222222");
                //System.out.println("2222222222222");

                boolean autoAck = false;

                String consumTag = "";

                channel.basicConsume(LOG_LEVEL_ARR[0],new DefaultConsumer(channel){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String message = new String(body, "UTF-8");
                        System.out.println("Customer Received '" + message + "'");

                        channel.basicAck(envelope.getDeliveryTag(),true);

                    }
                });
            }

        }catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
