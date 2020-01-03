package person.jzh.hello.syn.day01;

/**
 * @author jzh
 * @version 1.0.0
 * @title UnsafeTest01
 * @date 2019/12/15 19:45
 * @description：线程不安全：取票
 */
public class UnsafeTest01 {
    public static void main(String[] args) {
        // 一份资源
        UnsafeWeb12306 web = new UnsafeWeb12306();
        System.out.println(Thread.currentThread().getName());
        // 多个代理
        new Thread(web, "码畜").start();
        new Thread(web, "码农").start();
        new Thread(web, "码蟥").start();
    }
}
class UnsafeWeb12306 implements Runnable {
    // 票数
    private int ticketNums = 10;

    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            test();
        }
    }

    public void test(){
        if (ticketNums < 0) {
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