package person.jzh.hello.others.day01;

import java.util.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title TimerTest01
 * @date 2019/12/16 16:56
 * @description：任务调度：Timer和TimerTask
 */
public class TimerTest01 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        // 执行一次
        // timer.schedule(new MyTask(), 1000);
        // 执行多次
        //timer.schedule(new MyTask(), 1000, 200);
        // 定义开始执行的时间
        Calendar cal = new GregorianCalendar(2099, 12, 31, 21, 53, 54);
        timer.schedule(new MyTask(), cal.getTime(), 200);
    }
}

// 任务类
class MyTask extends TimerTask {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("放空大脑休息一会");
        }
        System.out.println("-------------end--------------");
    }
}
