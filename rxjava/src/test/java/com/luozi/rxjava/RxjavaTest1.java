package com.luozi.rxjava;

import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.util.ajax.JSONObjectConvertor;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;

import static javafx.scene.input.KeyCode.F;


/**
 * Created by luoziyihao on 7/29/16.
 */
public class RxjavaTest1 {

    @Test
    public void test1() {
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("hello world. ");
                        subscriber.onCompleted();
                    }
                }
        );

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                info("i am complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                info(s);
            }
        };

        observable.subscribe(subscriber);
    }

    private void info(String s) {
        System.out.println(s);
    }

    @Test
    public void test2() {
        Observable<String> observable = Observable.just("Hello, world", "i am test str2 ");
        observable
                .map(new Func1<String, String>() {      // map and func1 to create new data
                    @Override
                    public String call(String string) {
                        return string + " | " + String.valueOf(string.hashCode());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        info(s);
                    }
                });
    }

    @Test
    public void test3() {
        Observable.just(Arrays.asList("1", "2", "3", "4"))
                .map(new Func1<List<String>, Integer>() {
                    @Override
                    public Integer call(List<String> strings) {
                        for (String str : strings) {
                            info(str);
                        }
                        return strings.size();
                    }
                });
    }

    @Test
    public void test4() {
       Observable.from(Arrays.asList("Hello, world", "i am test str2 "))
               .map(new Func1<String, String>() {
                   @Override
                   public String call(String s) {
                       return s;
                   }
               })
               .flatMap(new Func1<String, Observable<String>>() {
                   @Override
                   public Observable<String> call(String s) {
                       return Observable.just(s + "___1", s + "___2", s + "___3");
                   }
               })
                .map(new Func1<String, String>() {      // map and func1 to create new data
                    @Override
                    public String call(String string) {
                        return string + " | " + String.valueOf(string.hashCode());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        info(s);
                    }
                });
    }

    @Test
    public void test5() {
        Observable.range(0,5)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }
}
