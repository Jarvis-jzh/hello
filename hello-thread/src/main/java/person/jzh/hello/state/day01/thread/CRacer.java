package person.jzh.hello.thread;

import java.util.concurrent.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title Racer
 * @date 2019/12/11 14:47
 * @description：模拟龟兔赛跑
 */
public class CRacer implements Callable<Integer> {

    // 胜利者
    private static String winner;

    @Override
    public Integer call() throws Exception {
        for (int steps = 1; steps <= 100; steps++) {
            // 模拟休息
            if (steps % 10 == 0 && Thread.currentThread().getName().equals("pool-1-thread-1")) {
                Thread.sleep(1);
            }

            System.out.println(Thread.currentThread().getName() + " ---> " + steps);
            // 比赛是否结束
            boolean flag = gameOver(steps);
            if (flag) {
                return steps;
            }
        }
        return null;
    }

    private boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        } else {
            if (steps == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner ===> " + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CRacer racer = new CRacer();
        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        // 提交执行
        Future<Integer> result1 = ser.submit(racer);
        Future<Integer> result2 = ser.submit(racer);
        // 获取结果
        Integer r1 = result1.get();
        Integer r2 = result1.get();
        System.out.println(r2);
        // 关闭服务
        ser.shutdownNow();
    }
}
