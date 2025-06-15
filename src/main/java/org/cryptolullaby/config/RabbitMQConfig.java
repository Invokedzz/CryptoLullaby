package org.cryptolullaby.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.register.email.queue}")
    private String registerQueue;

    @Value("${rabbitmq.reactivation.email.queue}")
    private String reactivationQueue;

    @Value("${rabbitmq.deactivation.email.queue}")
    private String deactivationQueue;

    private static final boolean IS_DURABLE = true;

    @Bean
    public Queue registerEmailQueue () {

        return new Queue(registerQueue, IS_DURABLE);

    }

    @Bean
    public Queue reactivationEmailQueue () {

        return new Queue(reactivationQueue, IS_DURABLE);

    }

    @Bean
    public Queue deactivationEmailQueue () {

        return new Queue(deactivationQueue, IS_DURABLE);

    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter () {

        var mapper = new ObjectMapper();

        return new Jackson2JsonMessageConverter(mapper);

    }

}
