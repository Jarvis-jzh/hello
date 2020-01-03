package person.jzh.hello.others.day03;

/**
 * @author jzh
 * @version 1.0.0
 * @title VolatileTest
 * @date 2019/12/16 18:15
 * @description：volatile用于保证数据的同步，也就是可见性
 */
public class VolatileTest {
    // private static int num = 0;

    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            // 此处不要编写代码，否则会看不到效果
            while(num == 0){
                 // System.out.println("qqq");
            }
        }).start();

        Thread.sleep(1000);
        num = 1;
    }
}
