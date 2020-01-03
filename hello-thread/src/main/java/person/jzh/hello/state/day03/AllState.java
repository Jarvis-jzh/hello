package person.jzh.hello.state.day03;

import java.lang.Thread.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title AllState
 * @date 2019/12/15 18:41
 * @description： 观察线程的状态
 */
public class AllState {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("...");
        });
        // 观察状态
        State state = t.getState(); // NEW
        System.out.println(state);

        t.start();
        state = t.getState(); // RUNNABLE
        System.out.println(state);

//        while (state != State.TERMINATED) {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            state = t.getState(); // TIMED_WAITING
//            System.out.println(state);
//        }
//        state = t.getState(); // TERMINATED
//        System.out.println(state);

        System.out.println("---------------------------------------------");

        while (true) {
            // 活动的线程数
            int num = Thread.activeCount();
            if (num == 2){
                break;
            }
            System.out.println(num);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = t.getState(); // timed_waiting
            System.out.println(state);
        }
//        state = t.getState(); // terminated
//        System.out.println(state);
    }
}
