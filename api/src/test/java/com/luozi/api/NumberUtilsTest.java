package com.luozi.api;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by luoziyihao on 6/19/17.
 */
public class NumberUtilsTest {

    @Test
    public void numberToVoice() throws Exception {
        Assert.assertEquals("一", NumberUtils.numberToVoice(1));
        Assert.assertEquals("一十一", NumberUtils.numberToVoice(11));
        Assert.assertEquals("一百一十一", NumberUtils.numberToVoice(111));
        Assert.assertEquals("一千一百一十一", NumberUtils.numberToVoice(1111));
        Assert.assertEquals("一万二千三百四十", NumberUtils.numberToVoice(12340));
        Assert.assertEquals("一万二千三百四十五", NumberUtils.numberToVoice(12345));

        Assert.assertEquals("一万零四十五", NumberUtils.numberToVoice(10045));
        Assert.assertEquals("零", NumberUtils.numberToVoice(0));
        Assert.assertEquals("一十", NumberUtils.numberToVoice(10));
        Assert.assertEquals("一百", NumberUtils.numberToVoice(100));
        Assert.assertEquals("一百零一", NumberUtils.numberToVoice(101));


        Assert.assertEquals("一亿零三百万零三百零一", NumberUtils.numberToVoice(103000301));
        Assert.assertEquals("二十一亿零三百万零三百零一", NumberUtils.numberToVoice(2103000301));
        Assert.assertEquals("二十一亿零三百零一", NumberUtils.numberToVoice(2100000301));
        Assert.assertEquals("二十一亿零三百", NumberUtils.numberToVoice(2100000300));
        Assert.assertEquals("二十一亿", NumberUtils.numberToVoice(2100000000));
        Assert.assertEquals("一百零一", NumberUtils.numberToVoice(101));

    }

}