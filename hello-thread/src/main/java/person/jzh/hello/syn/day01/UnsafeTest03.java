package person.jzh.hello.syn.day01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title UnsafeTest02
 * @date 2019/12/16 8:27
 * @description：线程不安全：操作容器
 */
public class UnsafeTest03 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i ++){
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        System.out.println(list.size());
    }
}

