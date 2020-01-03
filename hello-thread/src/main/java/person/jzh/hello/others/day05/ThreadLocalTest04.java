package person.jzh.hello.others.day05;

/**
 * @author jzh
 * @version 1.0.0
 * @title ThreadLocalTest01
 * @date 2019/12/17 9:01
 * @description：
 * InheritableThreadLocal：继承上下文，环境的数据，起点
 */
public class ThreadLocalTest04 {

    private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(2);
        System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());

        // 线程由main线程开辟，会将main的数据拷贝一份到自己的线程中
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
            threadLocal.set(200);
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
        }).start();
    }

}
