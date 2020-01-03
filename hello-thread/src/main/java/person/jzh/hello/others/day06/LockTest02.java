package person.jzh.hello.others.day06;

/**
 * @author jzh
 * @version 1.0.0
 * @title LockTest
 * @date 2019/12/17 10:12
 * @description： 不可重入锁：锁不可以延续使用
 */
public class LockTest02 {
    Lock lock = new Lock();

    public void a() throws InterruptedException {
        lock.lock();
        b();
        lock.unlock();
    }

    // 不可重入
    public void b() throws InterruptedException {
        lock.lock();

        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest02 test02 = new LockTest02();
        test02.a();
        test02.b();
    }
}

// 不可重入锁
class Lock {
    // 是否占用
    private boolean isLocked = false;

    // 使用锁
    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    // 释放锁
    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
