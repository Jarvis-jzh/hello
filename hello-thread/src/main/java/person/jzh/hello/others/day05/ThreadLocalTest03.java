package person.jzh.hello.others.day05;

/**
 * @author jzh
 * @version 1.0.0
 * @title ThreadLocalTest01
 * @date 2019/12/17 9:01
 * @description： ThreadLocal：分析上下文环境
 * 1、构造器：哪里调用，就属于哪里
 * 2、非构造器：本线程自身的
 */
public class ThreadLocalTest03 {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        new Thread(new MyRun()).start();
    }

    public static class MyRun implements Runnable {

        public MyRun() {
            threadLocal.set(100);
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 还剩下--> " + threadLocal.get());
        }
    }
}
