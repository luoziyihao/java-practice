package com.luozi.springpractice.applicationContext;

import org.springframework.stereotype.Component;

/**
 * Created by luoziyihao on 8/7/16.
 */
@Component
public class Msg {
    private String ZH = "wo ai ni ";
    private String EN = "i l U ";

    public String getEN() {
        return EN;
    }

    public String getZH() {
        return ZH;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "ZH='" + ZH + '\'' +
                ", EN='" + EN + '\'' +
                '}';
    }
}
