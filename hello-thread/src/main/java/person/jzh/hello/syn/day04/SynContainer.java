package person.jzh.hello.syn.day04;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jzh
 * @version 1.0.0
 * @title UnsafeTest02
 * @date 2019/12/16 8:27
 * @description：线程安全：操作容器
 */
public class SynContainer {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(list.size());
    }
}

