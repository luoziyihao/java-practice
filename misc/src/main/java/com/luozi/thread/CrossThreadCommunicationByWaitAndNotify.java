package com.luozi.thread;

/**
 * Created by luoziyihao on 6/20/17.
 */
public class CrossThreadCommunicationByWaitAndNotify {


    public void start() {
        Object lock = new Object();
        boolean[] locked_1 = {false};

        Runnable consumerRunner1 = getRunnable1(lock, locked_1);

        final boolean[] locked_2 = new boolean[1];
        Runnable consumerRunner2 = getRunnable2(lock, locked_2);

        Thread thread1 = new Thread(consumerRunner1);
        Thread thread2 = new Thread(consumerRunner2);

        thread1.start();
        thread2.start();

        sleepForStart();

        doNotify(lock, locked_1[0] && locked_2[0]);


    }

    private void doNotify(Object lock, boolean b) {
        synchronized (lock) {
            while (b) {

                System.out.println("i am notify now");
                lock.notifyAll();
                break;
            }
        }
    }

    private void sleepForStart() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Runnable getRunnable2(final Object lock, final boolean[] locked_2) {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("i am start for wait 2");
                doWait(lock, locked_2);
                System.out.println("i am wait ok , 2");

            }
        };
    }

    private Runnable getRunnable1(final Object lock, final boolean[] locked_1) {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("i am start for wait 1");
                doWait(lock, locked_1);
                System.out.println("i am wait ok, 1");


            }
        };
    }

    private void doWait(Object lock, boolean[] locked_1) {
        synchronized (lock) {
            try {
                locked_1[0] = true;
                lock.wait();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static void main(String[] args) {
        new CrossThreadCommunicationByWaitAndNotify().start();
    }
}
