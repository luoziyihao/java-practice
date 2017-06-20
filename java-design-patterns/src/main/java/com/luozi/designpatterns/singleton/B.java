package com.luozi.designpatterns.singleton;

public class B {
    private static B INSTANCE;

    private B() {
    }

    public static B getInstance() {
        if (INSTANCE == null) {
            // 当 INSTANCE 为 null的时候, 使用锁, 保证只生成一个 实例
            // 只在 INSTANCE 为null的时候使用锁, 而不是在方法上使用 锁, 提高效率
            synchronized (B.class) {
                // 在拿到索后, 还需要再次校验一下是否为 null, 因为可能好几个线程都走到拿到锁这一步了
                if (INSTANCE == null) {
                    INSTANCE = new B();
                }
            }
        }
        return INSTANCE;
    }
}