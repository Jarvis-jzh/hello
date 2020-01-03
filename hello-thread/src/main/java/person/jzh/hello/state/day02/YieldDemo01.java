package person.jzh.hello.state.day02;

/**
 * @author jzh
 * @version 1.0.0
 * @title YieldDemo
 * @date 2019/12/15 17:06
 * @description：yield 礼让线程，暂停线程，直接进入就绪状态而不是阻塞状态
 */
public class YieldDemo01 {
    public static void main(String[] args) {
        MyYield my = new MyYield();
        new Thread(my, "a").start();
        new Thread(my, "b").start();
    }

}

class MyYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ---> start");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " ---> end");
    }
}
