package com.luozi.springpractice.integration.service.impl;

import com.luozi.springpractice.integration.service.GreeterService;
import com.luozi.springpractice.integration.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by luoziyihao on 8/6/16.
 */
@Service
public class GreeterServiceImpl implements GreeterService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    @Named("helloWorldChannel")
    private MessageChannel messageChannel;

    @Inject
    private HelloService helloWorldGateway;

    @Override
    public void greet(String name) {
        messageChannel.send(MessageBuilder.withPayload(name).build());
    }

    @Override
    public void greet2(String name) {
        logger.info(helloWorldGateway.getHelloMessage(name));
    }
}
