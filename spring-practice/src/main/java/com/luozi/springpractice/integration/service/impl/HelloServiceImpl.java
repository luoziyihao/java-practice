package com.luozi.springpractice.integration.service.impl;

import com.luozi.springpractice.integration.service.HelloService;
import org.springframework.stereotype.Service;

import static com.luozi.log.LogUtils.debug;
import static com.luozi.log.LogUtils.info;

/**
 * Created by luoziyihao on 8/6/16.
 */
@Service
public class HelloServiceImpl implements HelloService{

    @Override
    public void hello(String name) {
        debug("hello " + name);
    }

    @Override
    public String getHelloMessage(String name) {
        return "hello " + name;
    }
}
