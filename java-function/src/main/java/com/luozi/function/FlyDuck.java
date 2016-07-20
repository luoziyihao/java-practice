package com.luozi.function;

import com.luozi.log.LogUtils;

import static com.luozi.log.LogUtils.info;

/**
 * Created by luoziyihao on 7/9/16.
 */
public class FlyDuck implements Fly {

    public void fly() {
        LogUtils.info("i can fly ");
    }
}
