package person.jzh.hello.thread;

/**
 * @author jzh
 * @version 1.0.0
 * @title Racer
 * @date 2019/12/11 14:47
 * @description：模拟龟兔赛跑
 */
public class Racer implements Runnable {

    // 胜利者
    private static String winner;

    @Override
    public void run() {
        for (int steps = 1; steps <= 100; steps++) {
            // 模拟休息
            if (steps % 10 == 0 &&Thread.currentThread().getName().equals("rabbit")){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + " ---> " + steps);
            // 比赛是否结束
            boolean flag = gameOver(steps);
            if (flag) {
                break;
            }
        }
    }

    private boolean gameOver(int steps){
        if (winner != null){
            return true;
        }else{
            if (steps == 100){
                winner = Thread.currentThread().getName();
                System.out.println("winner ===> " + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Racer racer = new Racer();
        new Thread(racer, "tortoise").start();
        new Thread(racer, "rabbit").start();
    }
}
