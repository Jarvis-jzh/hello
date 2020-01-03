package person.jzh.hello.others.day06;

/**
 * @author jzh
 * @version 1.0.0
 * @title LockTest
 * @date 2019/12/17 10:12
 * @description： 可重入锁：锁可以延续使用 + 计数器
 */
public class LockTest03 {
    ReLock lock = new ReLock();

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
        LockTest03 test03 = new LockTest03();
        test03.a();
        // test03.b();

        Thread.sleep(1000);

        System.out.println(test03.lock.getHoldCount());
    }
}
// 可重入锁
class ReLock {
    // 是否占用
    private boolean isLocked = false;
    // 存储线程
    private Thread lockedBy = null;
    // 统计锁的使用次数
    private int holdCount = 0;

    // 使用锁
    public synchronized void lock() throws InterruptedException {
        Thread t = Thread.currentThread();
        while (isLocked && lockedBy != t) {
            wait();
        }
        isLocked = true;
        lockedBy = t;
        holdCount++;
    }

    // 释放锁
    public synchronized void unlock() {
        if (Thread.currentThread() == lockedBy){
            holdCount--;
            if (holdCount == 0){
                isLocked = false;
                notify();
                lockedBy = null;
            }

        }
    }

    public int getHoldCount() {
        return holdCount;
    }
}

