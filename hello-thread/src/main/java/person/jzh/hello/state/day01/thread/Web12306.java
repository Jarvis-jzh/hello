package person.jzh.hello.thread;

/**
 * @author jzh
 * @version 1.0.0
 * @title Web12306
 * @date 2019/12/11 14:41
 * @description：
 */
public class Web12306 implements Runnable {

    // 票数
    private int ticketNums = 99;

    @Override
    public void run() {
        while (true) {
            if (ticketNums < 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ----> " +ticketNums--);
        }
    }

    public static void main(String[] args) {
        // 一份资源
        Web12306 web = new Web12306();
        System.out.println(Thread.currentThread().getName());
        // 多个代理
        new Thread(web, "码畜").start();
        new Thread(web, "码农").start();
        new Thread(web, "码蟥").start();
    }
}
