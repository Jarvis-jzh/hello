package person.jzh.hello.others.day06;

/**
 * @author jzh
 * @version 1.0.0
 * @title LockTest
 * @date 2019/12/17 10:12
 * @description：
 * 可重入锁：锁可以延续使用
 */
public class LockTest01 {
    public void test() {
        // 第一次获得锁
        synchronized(this){
            while(true){
                // 第二次获得同样的锁
                synchronized(this){
                    System.out.println("ReentrantLock!");
                }
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new LockTest01().test();
    }
}
