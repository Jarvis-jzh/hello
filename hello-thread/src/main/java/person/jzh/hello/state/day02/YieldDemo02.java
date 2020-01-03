package person.jzh.hello.state.day02;

/**
 * @author jzh
 * @version 1.0.0
 * @title YieldDemo
 * @date 2019/12/15 17:06
 * @description：yield 礼让线程，暂停线程，直接进入就绪状态而不是阻塞状态
 */
public class YieldDemo02 {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("lambda");
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            if (i % 20 == 0) {
                Thread.yield();
            }
            System.out.println("main --> " + i);
        }
    }

}
