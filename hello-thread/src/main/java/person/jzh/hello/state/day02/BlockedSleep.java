package person.jzh.hello.state.day02;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jzh
 * @version 1.0.0
 * @title BlockedSleep
 * @date 2019/12/15 16:57
 * @description：
 */
public class BlockedSleep {
    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    public static void test2() throws InterruptedException {
        Date end = new Date(System.currentTimeMillis() + 1000 * 10);
        long e = end.getTime();
        boolean flag = true;
        while (flag){
            System.out.println(new SimpleDateFormat("mm:ss").format(end));
            Thread.sleep(1000);
            end = new Date(end.getTime() - 1000);
            if (e - 10000 > end.getTime()){
                flag = false;
            }
        }
    }

    public static void test1() throws InterruptedException {
        int num = 10;
        boolean flag = true;
        while (flag){
            Thread.sleep(1000);
            System.out.println(num--);
            if (num < 0) {
                flag = false;
            }
        }
    }
}
