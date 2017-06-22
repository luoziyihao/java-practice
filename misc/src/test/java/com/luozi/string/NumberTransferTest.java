package com.luozi.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by luoziyihao on 6/19/17.
 */
public class NumberTransferTest {

    @Test
    public void numberToVoice() throws Exception {
        Assert.assertEquals("一", NumberTransfer.numberToVoice(1));
        Assert.assertEquals("一十一", NumberTransfer.numberToVoice(11));
        Assert.assertEquals("一百一十一", NumberTransfer.numberToVoice(111));
        Assert.assertEquals("一千一百一十一", NumberTransfer.numberToVoice(1111));
        Assert.assertEquals("一万二千三百四十", NumberTransfer.numberToVoice(12340));
        Assert.assertEquals("一万二千三百四十五", NumberTransfer.numberToVoice(12345));

        Assert.assertEquals("一万零四十五", NumberTransfer.numberToVoice(10045));
        Assert.assertEquals("零", NumberTransfer.numberToVoice(0));
        Assert.assertEquals("一十", NumberTransfer.numberToVoice(10));
        Assert.assertEquals("一百", NumberTransfer.numberToVoice(100));
        Assert.assertEquals("一百零一", NumberTransfer.numberToVoice(101));


        Assert.assertEquals("一亿零三百万零三百零一", NumberTransfer.numberToVoice(103000301));
        Assert.assertEquals("二十一亿零三百万零三百零一", NumberTransfer.numberToVoice(2103000301));
        Assert.assertEquals("二十一亿零三百零一", NumberTransfer.numberToVoice(2100000301));
        Assert.assertEquals("二十一亿零三百", NumberTransfer.numberToVoice(2100000300));
        Assert.assertEquals("二十一亿", NumberTransfer.numberToVoice(2100000000));
        Assert.assertEquals("一百零一", NumberTransfer.numberToVoice(101));

    }

}