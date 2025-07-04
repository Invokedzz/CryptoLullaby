package org.cryptolullaby.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cryptolullaby.util.IRabbitQueuesConfigMethods;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig implements IRabbitQueuesConfigMethods {

    @Value("${rabbitmq.register.email.queue}")
    private String registerQueue;

    @Value("${rabbitmq.reactivation.email.queue}")
    private String reactivationQueue;

    @Value("${rabbitmq.deactivation.email.queue}")
    private String deactivationQueue;

    @Value("${rabbitmq.confirm.report.email.queue}")
    private String confirmReportQueue;

    private static final boolean IS_DURABLE = true;

    @Bean
    @Override
    public Queue registerEmailQueue () {

        return buildQueue(registerQueue);

    }

    @Bean
    @Override
    public Queue reactivationEmailQueue () {

        return buildQueue(reactivationQueue);

    }

    @Bean
    @Override
    public Queue deactivationEmailQueue () {

        return buildQueue(deactivationQueue);

    }

    @Bean
    @Override
    public Queue confirmReportEmailQueue () {

        return buildQueue(confirmReportQueue);

    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter () {

        var mapper = new ObjectMapper();

        return new Jackson2JsonMessageConverter(mapper);

    }

    private Queue buildQueue (String name) {

        return new Queue(name, IS_DURABLE);

    }

}
