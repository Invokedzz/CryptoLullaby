package org.cryptolullaby.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.email.queue.name}")
    private String emailQueue;

    private static final boolean IS_DURABLE = true;

    @Bean
    public Queue getEmailQueue () {

        return new Queue(emailQueue, IS_DURABLE);

    }

}
