package com.luozi.springpractice.applicationContext;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.luozi.log.LogUtils.debug;

public class MainApp {
    public static void main(String[] args) {
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld obj   = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
        HelloWorld2 helloWorld2 = context.getBean(HelloWorld2.class);
        debug(helloWorld2.getMsg());
    }
}