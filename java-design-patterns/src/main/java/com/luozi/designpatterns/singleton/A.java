package com.luozi.designpatterns.singleton;

public class A {
    private final static A INSTANCE = new A();

    private A() {
    }

    public static A getInstance() {
        return INSTANCE;
    }
}