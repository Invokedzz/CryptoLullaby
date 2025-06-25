package org.cryptolullaby.util;

import org.springframework.amqp.core.Queue;

public interface IRabbitQueuesConfig {

    Queue registerEmailQueue ();

    Queue reactivationEmailQueue ();

    Queue deactivationEmailQueue ();

    Queue confirmReportEmailQueue ();

    Queue denyReportEmailQueue ();

}
