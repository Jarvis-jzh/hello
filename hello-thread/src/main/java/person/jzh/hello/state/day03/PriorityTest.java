package person.jzh.hello.state.day03;

/**
 * @author jzh
 * @version 1.0.0
 * @title PriorityTest
 * @date 2019/12/15 18:59
 * @description：
 * 线程的优先级 1-10
 * 1、NORM_PRIORITY 5 默许
 * 2、MIN_PRIORITY 1
 * 3、MAX_PRIORITY 10
 */
public class PriorityTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " --> " +Thread.currentThread().getPriority());
        MyPriority mp = new MyPriority();
        Thread t1 = new Thread(mp, "1");
        Thread t2 = new Thread(mp, "2");
        Thread t3 = new Thread(mp, "3");
        Thread t4 = new Thread(mp, "4");
        Thread t5 = new Thread(mp, "5");
        Thread t6 = new Thread(mp, "6");


        // 设置优先级要在启动前
        t1.setPriority(1);
        t2.setPriority(2);
        t3.setPriority(3);
        t4.setPriority(4);
        t5.setPriority(5);
        t6.setPriority(6);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}

class MyPriority implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " --> " +Thread.currentThread().getPriority());
        Thread.yield();
    }
}
