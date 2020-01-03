package person.jzh.hello.syn.day02;

/**
 * @author jzh
 * @version 1.0.0
 * @title SynTest01
 * @date 2019/12/16 9:21
 * @description：线程安全：在并发时保证数据的正确性、效率尽可能高
 */
public class SynTest01 {
    public static void main(String[] args) {
        // 一份资源
        SynWeb12306 web = new SynWeb12306();
        System.out.println(Thread.currentThread().getName());
        // 多个代理
        new Thread(web, "码畜").start();
        new Thread(web, "码农").start();
        new Thread(web, "码蟥").start();
    }
}
class SynWeb12306 implements Runnable {
    // 票数
    private int ticketNums = 10;

    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test();
        }
    }

    // 线程安全  同步
    public synchronized void test(){
        if (ticketNums < 1) {
            this.flag = false;
            return;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ----> " +ticketNums--);
    }

}
