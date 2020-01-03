package person.jzh.hello.thread;

/**
 * @author jzh
 * @version 1.0.0
 * @title StartRun
 * @date 2019/12/11 10:51
 * @description：
 */
public class StartRun implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("一边听歌");
        }
    }

    public static void main(String[] args) {
        // 1、
        // 创建实现类对象
        StartRun st = new StartRun();
        // 创建代理类对象
        Thread t = new Thread(st);
        // 启动
        t.start();

        // 2、匿名实现
//        new Thread(new StartRun()).start();

        for (int i = 0; i < 200; i++) {
            System.out.println("一边coding");
        }
    }
}
