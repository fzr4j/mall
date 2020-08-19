package com.fzr.mqtest.pc;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {

    private static final String QUEUE_NAME = "local::queue:02";

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
        /**
         * queue: 要创建的队列名
         * durable: 是否持久化
         * exclusive: true表示一个队列只能被一个消费者占有并消费
         * autoDelete: true表示服务器不再使用这个队列时会自动删除
         * arguments: 其他参数
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, "this is a test msg 2".getBytes("UTF-8"));
        channel.close();
        connection.close();
    }
}
