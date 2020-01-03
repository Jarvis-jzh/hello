package person.jzh.hello.others.day05;

/**
 * @author jzh
 * @version 1.0.0
 * @title ThreadLocalTest01
 * @date 2019/12/17 9:01
 * @description：
 * ThreadLocal：每个线程自身的数据，更改不会影响其它线程
 */
public class ThreadLocalTest02 {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyRun()).start();
        }
    }

    public static class MyRun implements Runnable {

        @Override
        public void run() {
            Integer left = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + " 得到了--> " + left);
            threadLocal.set(left - 1);
            System.out.println(Thread.currentThread().getName() + " 还剩下--> " + threadLocal.get());
        }
    }
}
