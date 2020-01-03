package person.jzh.hello.state.day02;

/**
 * @author jzh
 * @version 1.0.0
 * @title YieldDemo
 * @date 2019/12/15 17:06
 * @description：yield join：合并线程，插队线程
 */
public class BlockedJoin02 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("爸爸和儿子买烟的故事");
        Thread thread = new Thread(new Father());
        thread.start();
    }

}
class Father extends Thread {
    public void run(){
        System.out.println("想抽烟，发现没了");
        System.out.println("让儿子去买中华");
        Thread t = new Thread(new Son());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("孩子走丢了，老爸出去找孩子了。。。");
        }
        System.out.println("老爸接过烟，把零钱给了儿子");
    }
}
class Son extends Thread {
    public void run(){
        System.out.println("接过老爸的钱出去了。。。");
        System.out.println("路边有个游戏厅，玩了10秒");
        for (int i = 0; i < 10; i++){
            System.out.println(i + "秒过去了。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("赶紧买烟去。。。");
        System.out.println("手拿一包中华回家。。。");
    }
}
