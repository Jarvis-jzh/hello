package person.jzh.hello.thread.lambda;

/**
 * @author jzh
 * @version 1.0.0
 * @title LambdaTest04
 * @date 2019/12/11 17:49
 * @description：
 */
public class LambdaTest04 {
    public static void main(String[] args) {
        new Thread(() -> {
//            Thread.yield();
            System.out.println("1、一边学习lambda");
        });

        new Thread(() -> System.out.println("2、一边学习lambda")).start();
    }
}
