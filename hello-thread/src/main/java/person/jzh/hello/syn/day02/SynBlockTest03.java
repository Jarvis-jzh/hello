package person.jzh.hello.syn.day02;

/**
 * @author jzh
 * @version 1.0.0
 * @title SynTest01
 * @date 2019/12/16 9:21
 * @description：线程安全：在并发时保证数据的正确性、效率尽可能高
 */
public class SynBlockTest03 {
    public static void main(String[] args) {
        // 一份资源
        SynBlockWeb12306 web = new SynBlockWeb12306();
        System.out.println(Thread.currentThread().getName());
        // 多个代理
        new Thread(web, "码畜").start();
        new Thread(web, "码农").start();
        new Thread(web, "码蟥").start();
    }
}

class SynBlockWeb12306 implements Runnable {
    // 票数
    private int ticketNums = 10;

    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test5();
        }
    }

    // 线程安全  同步
    public synchronized void test1() {
        if (ticketNums < 1) {
            this.flag = false;
            return;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ----> " + ticketNums--);
    }


    // 线程安全  同步块    范围太大，性能不高
    public void test2() {
        synchronized (this) {
            if (ticketNums < 1) {
                this.flag = false;
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ----> " + ticketNums--);
        }
    }


    // 线程不安全 ticketNums对象在变
    public void test3() {
        synchronized ((Integer) ticketNums) {
            if (ticketNums < 1) {
                this.flag = false;
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ----> " + ticketNums--);
        }
    }


    // 线程不安全，范围太小锁不住
    public void test4() {
        synchronized (this) {
            if (ticketNums < 1) {
                this.flag = false;
                return;
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ----> " + ticketNums--);

    }

    // 线程安全：尽可能锁定合理的范围（不是指代码，指数据的完整性）
    public void test5() {
        // 双重检测（double checking）
        // 考虑的是没有票的情况
        if (ticketNums <= 0) {
            this.flag = false;
            return;
        }
        synchronized (this) {
            // 考虑最后的一张票
            if (ticketNums <= 0) {
                this.flag = false;
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ----> " + ticketNums--);
        }
    }
}
