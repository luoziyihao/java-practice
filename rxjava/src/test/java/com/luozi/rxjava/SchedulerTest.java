package com.luozi.rxjava;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by luoziyihao on 9/9/16.
 */

public class SchedulerTest {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() {
        logger.info("start on Thread -> {} ", Thread.currentThread().getName());
        logger.info(scheduler1()) ;
//        logger.info("end on Thread -> {} ", Thread.currentThread().getName());

    }


    public String scheduler1 () {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                logger.info("OnSubscribe.call Thread -> {}" + Thread.currentThread().getName());
                subscriber.onNext("message");
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        logger.info("Subscriber.onNext Thread -> {}" + Thread.currentThread().getName());
                    }
                });

//        try {
//            Thread.sleep(20L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "ok";
    }


    @Test
    public void scheduler2() {
        System.out.println("main thread start");    // log 耗时长, 不好比较 所以替换成 out
        Observable.just(ImmutableList.of(1, 2, 3))
                .flatMap(new Func1<ImmutableList<Integer>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(ImmutableList<Integer> integers) {
                        logger.info("integer {}, flatMap call Thread {}", integers, Thread.currentThread().getName());
                        return Observable.from(integers);
                    }
                })
                .subscribeOn(Schedulers.io())   // 被观察者生产使用的线程

                .observeOn(Schedulers.newThread())  // 观察者使用的线程

                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
//                        logger.error("onError", e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        logger.info("integer {}, Subscriber.onNext Thread {}", integer, Thread.currentThread().getName());
                        try {
                            if (integer == 2) {
                                throw new IllegalArgumentException("2");
                            }
                        } catch (Exception e) {
                            logger.error("onNext {}, error ",integer,  e);

                        }

                    }
                });

        sleep(100);
        System.out.println("main thread end");
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
