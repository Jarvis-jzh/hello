package person.jzh.hello.state.day04;

/**
 * @author jzh
 * @version 1.0.0
 * @title DaemonTest
 * @date 2019/12/15 19:10
 * @description：
 *
 */
public class DaemonTest {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();
        Thread t = new Thread(god);
        // 将用户线程调整为守护
        t.setDaemon(true);
        t.start();
        new Thread(you).start();
    }
}
class You implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i< 365*100;i++){
            System.out.println("happy life...");
        }
        System.out.println("ooooooo....");
    }
}
class God implements Runnable {
    @Override
    public void run() {
        for (;;){
            System.out.println("bless you...");
        }
    }
}