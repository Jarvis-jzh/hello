package person.jzh.hello.others.day05;

/**
 * @author jzh
 * @version 1.0.0
 * @title ThreadLocalTest01
 * @date 2019/12/17 9:01
 * @description： ThreadLocal：每个线程自身的存储本地、局部区域
 */
public class ThreadLocalTest01 {
//    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

//    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue() {
//            return 200;
//        }
//    };

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 200);

    public static void main(String[] args) {
        // 获取傎
        System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());

        // 设置值
        threadLocal.set(99);
        System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());

        new Thread(new MyRun()).start();
        new Thread(new MyRun()).start();
    }

    public static class MyRun implements Runnable {

        @Override
        public void run() {
            double v = Math.random() * 99;
            // System.out.println("v -> " + v);
            threadLocal.set((int) v);
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
        }
    }
}
