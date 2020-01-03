package person.jzh.hello.thread;

/**
 * @author jzh
 * @version 1.0.0
 * @title StartRun
 * @date 2019/12/11 10:51
 * @description：Lambda表达式 简化线程（用一次）的使用
 */
public class LambdaThread {

    // 静态内部类
    static class Test implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("一边听歌");
            }
        }
    }


    public static void main(String[] args) {
        // new Thread(new Test()).start();

        // 局部内部类
//        class Test2 implements Runnable {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println("一边听歌");
//                }
//            }
//        }
//
//        new Thread(new Test2()).start();

        // 匿名内部类  必须借助接口或者父类
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println("一边听歌");
//                }
//            }
//        }).start();

        // jdk8 简化 lambda
        new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    System.out.println("一边听歌");
                }
            }
        ).start();
    }
}
