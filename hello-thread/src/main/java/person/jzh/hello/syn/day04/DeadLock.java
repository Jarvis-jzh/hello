package person.jzh.hello.syn.day04;

/**
 * @author jzh
 * @version 1.0.0
 * @title DeadLock
 * @date 2019/12/16 15:02
 * @description：死锁
 */
public class DeadLock {
    public static void main(String[] args) {
        Markup g1 = new Markup(1, "张柏芝");
        Markup g2 = new Markup(0, "王菲");
        g1.start();
        g2.start();
    }
}

// 口红
class Lipstick {

}

// 镜子
class Mirror {

}

// 化妆
class Markup extends Thread {
    // 一面镜子，一支口红
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    // 选择
    int choice;
    // 名字
    String girl;

    public Markup(int choice, String girl) {
        this.choice = choice;
        this.girl = girl;
    }

    @Override
    public void run() {
        markup();
    }

    // 相互持有对方的对象锁->可能造成死锁
    private void markup() {
        if (choice == 0) {
            synchronized (lipstick) {
                // 获得口红的锁
                System.out.println(this.girl + "获得口红");
                // 1秒后想拥有镜子的锁
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                synchronized (mirror) {
//                    // 获得镜子的锁
//                    System.out.println(this.girl + "照镜子");
//                }
            }
            synchronized (mirror) {
                // 获得镜子的锁
                System.out.println(this.girl + "照镜子");
            }
        } else {
            synchronized (mirror) {
                // 获得镜子的锁
                System.out.println(this.girl + "照镜子");
                // 2秒后想拥有口红的锁
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                synchronized (lipstick) {
//                    // 获得口红的锁
//                    System.out.println(this.girl + "获得口红");
//                }
            }
            synchronized (lipstick) {
                // 获得口红的锁
                System.out.println(this.girl + "获得口红");
            }
        }
    }
}