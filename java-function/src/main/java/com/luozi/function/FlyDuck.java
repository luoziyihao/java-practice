package com.luozi.function;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luoziyihao on 7/9/16.
 */
public class FlyDuck implements Fly {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void fly() {
        logger.info("i can fly ");
    }
}
