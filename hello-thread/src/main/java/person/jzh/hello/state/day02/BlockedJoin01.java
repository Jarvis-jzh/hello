package person.jzh.hello.state.day02;

/**
 * @author jzh
 * @version 1.0.0
 * @title YieldDemo
 * @date 2019/12/15 17:06
 * @description：yield join：合并线程，插队线程
 */
public class BlockedJoin01 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("lambda --> " + i);
            }
        });
        t.start();

        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                // 插队，main被阻塞了
                t.join();
            }
            System.out.println("main --> " + i);
        }
    }

}
