package com.fzr.mqtest.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;

public class Producer {

    private static final String QUEUE_NAME = "local::mq02:exchange:e01";

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

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
        channel.exchangeDeclare(QUEUE_NAME, ExchangeTypes.FANOUT);
        channel.basicPublish("", QUEUE_NAME, null, "this is a fanout exchange message 1".getBytes("UTF-8"));
        System.out.println("消息发送成功");
        channel.close();
        connection.close();
    }
}
