package com.luozi.springpractice.applicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp {

    public static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
    public static void main(String[] args) {
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld obj   = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
        HelloWorld2 helloWorld2 = context.getBean(HelloWorld2.class);
        LOGGER.debug("{}", helloWorld2.getMsg());
    }
}