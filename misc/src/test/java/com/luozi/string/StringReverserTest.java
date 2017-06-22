package com.luozi.string;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by luoziyihao on 6/22/17.
 */
public class StringReverserTest {

    @Test
    public void reverseStringBySplitChar() throws Exception {
        String src = "qqq---qqq---123-ddd-adadfa";
        char split = '-';
        System.out.println(src);
        System.out.println(new StringReverser().reverseStringBySplitChar(src, split));
    }

}