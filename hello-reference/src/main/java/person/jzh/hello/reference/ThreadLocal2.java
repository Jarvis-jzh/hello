package person.jzh.hello.reference;

import java.util.concurrent.TimeUnit;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/28 15:12
 * @description
 */
public class ThreadLocal2 {

    static ThreadLocal<Person> t1 = new ThreadLocal<Person>();

    public static void main(String[] args) {
        new Thread(() -> {
           try{
               TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
               e.printStackTrace();
           }
            System.out.println(t1.get());
        }).start();

        new Thread(() -> {
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.set(new Person());
        }).start();
    }

    static class Person {
        String name = "zhangsan";
    }
}
