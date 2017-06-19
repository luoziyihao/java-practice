package com.luozi.function;

import com.luozi.log.LogUtils;
import org.junit.Test;

import static com.luozi.log.LogUtils.info;

/**
 * Created by luoziyihao on 7/9/16.
 */
public class DoTest {

    @Test
    public void testFly() throws Exception {
        Fly flyDuck = new FlyDuck();
        Do.fly(flyDuck);

        Do.fly(new Fly() {
            public void fly() {
                LogUtils.info("just test java function coding");
            }
        });
    }
}