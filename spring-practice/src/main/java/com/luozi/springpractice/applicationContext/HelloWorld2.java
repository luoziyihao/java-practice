package com.luozi.springpractice.applicationContext;

import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by luoziyihao on 8/7/16.
 */
@Component
public class HelloWorld2 {
    @Inject
    private Msg msg;

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }
}
