package person.jzh.hello.others.day07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jzh
 * @version 1.0.0
 * @title CAS
 * @date 2019/12/17 11:10
 * @description： CAS:比较并交换
 */
public class CAS {
    // 库存
    private static AtomicInteger stock = new AtomicInteger(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer left = stock.decrementAndGet();
                if (left < 1) {
                    System.out.println("抢完了。。。");
                    return;
                }
                System.out.print(Thread.currentThread().getName() + "抢了一件商品");
                System.out.println(" --> 还剩下" + stock);
            }).start();
        }
    }
}
