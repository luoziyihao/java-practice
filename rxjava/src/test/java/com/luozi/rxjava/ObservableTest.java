package com.luozi.rxjava;

import org.joda.time.DateTime;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.cglib.beans.BeanMap.create;

/**
 * Created by luoziyihao on 12/5/16.
 */
public class ObservableTest {

    public Observable<List<Integer>> array(Long millis) {
        System.out.println(millis + "-start");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(millis + "-end");
        return Observable.just(Arrays.asList(0,3));
    }

    @Test
    public void testZip() throws InterruptedException {
        long times = new Date().getTime();
        Observable.zip(
                array(1001L),
                array(1000L),
                array(1200L),
                (i, j, k) -> i + "____" + j + "_____" + k
        )
                .subscribe(s -> {
                    long times2 = new Date().getTime();
                    long interval = times2 - times;
                    System.out.println(interval);
                });
        Thread.sleep(6000L);
    }


    /**
     * 生产者默认是单线程同步的
     * @throws InterruptedException
     */
    @Test
    public void testZip2() throws InterruptedException {
        long times = new Date().getTime();
        Observable.zip(
                array2(1001L),
                array2(1000L),
                array2(1200L),
                (i, j, k) -> i + "____" + j + "_____" + k
        )
                .subscribe(s -> {
                    long times2 = new Date().getTime();
                    long interval = times2 - times;
                    System.out.println(interval);
                });
        System.out.println("main end");
        Thread.sleep(6000L);
    }

    private Observable<? extends List<Integer>> array2(long millis) {
        return Observable.create(new Observable.OnSubscribe<List<Integer>>() {
            @Override
            public void call(Subscriber<? super List<Integer>> subscriber) {
                System.out.println(millis + "-start");
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(Arrays.asList(0, 3));
                System.out.println(millis + "-end");
            }
        });
    }


    @Test
    public void testZip3() throws InterruptedException {
        long times = new Date().getTime();
        Observable.zip(
                array3(1001L),
                array3(1000L),
                array3(1200L),
                (i, j, k) -> i + "____" + j + "_____" + k
        )
                .subscribe(s -> {
                    long times2 = new Date().getTime();
                    long interval = times2 - times;
                    System.out.println(interval);
                });
        System.out.println("main end");
        Thread.sleep(6000L);
    }

    private Observable<? extends List<Integer>> array3(long millis) {
        return Observable.create(new Observable.OnSubscribe<List<Integer>>() {
            @Override
            public void call(Subscriber<? super List<Integer>> subscriber) {
                System.out.println(millis + "-start");
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(Arrays.asList(0, 3));
                System.out.println(millis + "-end");
            }
        })
                .subscribeOn(Schedulers.newThread());
    }
}
