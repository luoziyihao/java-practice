package com.luozi.springpractice.integration;

import com.luozi.springpractice.integration.service.GreeterService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luoziyihao on 8/6/16.
 */
public class App {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GreeterService greeterService = context.getBean("greeterServiceImpl", GreeterService.class);
        greeterService.greet("i l u");
        greeterService.greet("i l u 2");
        greeterService.greet2("i love you, use gateway, with response");
    }
}
