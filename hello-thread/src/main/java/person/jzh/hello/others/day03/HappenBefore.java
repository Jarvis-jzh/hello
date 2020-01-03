package person.jzh.hello.others.day03;

/**
 * @author jzh
 * @version 1.0.0
 * @title HappenBefore
 * @date 2019/12/16 17:52
 * @description： 指令重排：代码执行顺序与预期不一致
 * 目的：提高性能
 */
public class HappenBefore {
    private static int a = 0;
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++){
            a = 0;
            flag = false;

            // 线程1 更改数据
            Thread t1 = new Thread(() -> {
                a = 1;
                flag = true;
            });

            // 线程2 读取数据
            Thread t2 = new Thread(() -> {
                if (flag) {
                    a *= 1;
                }
                // 指令重排
                if (a == 0) {
                    System.out.println("happen before a -> " + a);
                }
            });

            t1.start();
            t2.start();

            // 合并线程
            t1.join();
            t2.join();
        }
    }
}
