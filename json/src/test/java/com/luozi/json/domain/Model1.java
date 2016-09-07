package com.luozi.json.domain;


/**
 * Created by luoziyihao on 8/7/16.
 */

public class Model1<T> extends AbstractBaseModel {

    int code ;
    String msg;

    private T items;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Model1{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", items=" + items +
                '}';
    }
}

