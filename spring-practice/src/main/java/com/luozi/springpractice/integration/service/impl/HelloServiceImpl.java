package com.luozi.springpractice.integration.service.impl;

import com.luozi.springpractice.integration.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by luoziyihao on 8/6/16.
 */
@Service
public class HelloServiceImpl implements HelloService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void hello(String name) {
        logger.debug("hello " + name);
    }

    @Override
    public String getHelloMessage(String name) {
        return "hello " + name;
    }
}
