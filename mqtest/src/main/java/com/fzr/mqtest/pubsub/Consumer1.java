package com.fzr.mqtest.pubsub;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;

public class Consumer1 {

    private static final String EXCHANGE_NAME = "local::mq02:exchange:e01";
    private static final String QUEUE_NAME_01 = "local::mq02:queue:q01";
    private static final String QUEUE_NAME_02 = "local::mq02:queue:q02";

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer1.class);

    public static void main(String[] args) throws Exception{
        //连接管理器，并设置连接参数
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.16.16.46");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //新建一个连接
        Connection connection = connectionFactory.newConnection();
        //新建一个通道
        Channel channel = connection.createChannel();
        /**
         * queue: 要创建的队列名
         * durable: 是否持久化
         * exclusive: true表示一个队列只能被一个消费者占有并消费
         * autoDelete: true表示服务器不再使用这个队列时会自动删除
         * arguments: 其他参数
         */
        channel.queueDeclare(QUEUE_NAME_01, true, false, false, null);
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.FANOUT);
        //在fanout类型的路由器中，路由键无效，所以设计为空字符串
        final String routeKey = "";
        //将这个队列订阅到这个路由器上，表示这个队列对这个路由器感兴趣
        channel.queueBind(QUEUE_NAME_01, EXCHANGE_NAME, routeKey);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                LOGGER.info("收件人一号收到消息:{}", message);
            }
        };
        //队列一确认收到消息
        channel.basicConsume(QUEUE_NAME_01, true, consumer);

        channel.close();
        connection.close();
    }
}
