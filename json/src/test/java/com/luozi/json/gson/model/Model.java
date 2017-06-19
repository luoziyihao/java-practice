package com.luozi.json.gson.model;


import com.luozi.json.entity.AbstractBaseModel;

/**
 * Created by luoziyihao on 8/7/16.
 */

public class Model<T> extends AbstractBaseModel {

    private int code ;
    private String msg;

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
        return "Model{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", items=" + items +
                '}';
    }
}

