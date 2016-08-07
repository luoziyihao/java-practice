package com.luozi.springdatajpa.service;

import com.luozi.springdatajpa.domain.User;
import com.luozi.springdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.luozi.log.LogUtils.info;

@Service
@Transactional
public class Client {
    @Autowired
    private UserRepository ur;

    public void testAdd(User um) {
        ur.save(um);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Client c = (Client) ctx.getBean("mainBean");
        User um = new User();
        um.setAge(1);
        um.setName("张三");
        c.testAdd(um);

        info(c.ur.findAll());
    }
}