package com.examples.messaging.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This live test requires:
 * rabbitmq instance running on the environment
 * 
 * <br>
 * To run rabbitmq using docker image:
 * (e.g. `docker run -d --name rabbitmq -p 5672:5672 rabbitmq:3`)
 * 
 */
@SpringBootTest(classes = SpringWebfluxAmqpApplication.class)
public class SpringContextLiveTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
