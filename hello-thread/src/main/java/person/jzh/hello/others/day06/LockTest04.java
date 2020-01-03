package person.jzh.hello.others.day06;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jzh
 * @version 1.0.0
 * @title LockTest
 * @date 2019/12/17 10:12
 * @description： 可重入锁：锁可以延续使用 + 计数器
 */
public class LockTest04 {
    ReentrantLock lock = new ReentrantLock();

    public void a() throws InterruptedException {
        lock.lock();
        System.out.println(lock.getHoldCount()); // 1
        b();
        lock.unlock();
        System.out.println(lock.getHoldCount()); // 0
    }

    // 不可重入
    public void b() throws InterruptedException {
        lock.lock();
        System.out.println(lock.getHoldCount()); // 2
        lock.unlock();
        System.out.println(lock.getHoldCount()); // 1
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest04 test04 = new LockTest04();
        test04.a();
        // test04.b();

        Thread.sleep(1000);

        System.out.println(test04.lock.getHoldCount());
    }
}

