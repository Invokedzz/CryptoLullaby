package org.cryptolullaby.util;

import org.springframework.amqp.core.Queue;

public interface IRabbitQueuesConfigMethods {

    Queue registerEmailQueue ();

    Queue reactivationEmailQueue ();

    Queue deactivationEmailQueue ();

    Queue confirmReportEmailQueue ();

}
