package com.luozi.extensive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoziyihao on 6/26/17.
 */
public class ExtensivePractice {

    public void testParameter(){
        List<Integer> integers = new ArrayList<>();
        doSomething(integers);
    }

//    public void doSomething(List<Object> numbers) {
//
//    }


    public void doSomething(List<? extends Number> numbers) {

    }

    public void testEvaluate() {
        List<Integer> integers = new ArrayList<>();
        List<? extends Number> numbers = integers;
    }
}
