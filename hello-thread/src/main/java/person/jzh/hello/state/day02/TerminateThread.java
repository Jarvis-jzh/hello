package person.jzh.hello.state.day02;

/**
 * @author jzh
 * @version 1.0.0
 * @title TerminateThread
 * @date 2019/12/15 16:37
 * @description：
 * 终止线程
 * 1、线程正常执行完毕->次数
 * 2、外部干涉->加入标识
 * 不要使用stop destroy
 */
public class TerminateThread implements Runnable {

    // 加入标识
    private boolean flag = true;

    private String name;

    public TerminateThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int i = 0;
        // 2、关联标识
        while (flag) {
            System.out.println("-->" + i++);
        }
    }

    // 3、对外提供方法改变标识
    public void terminate(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TerminateThread tt = new TerminateThread("C罗");
        new Thread(tt).start();

        for (int i = 0; i < 99 ; i++){
            if (i ==88){
                tt.terminate();
                System.out.println("tt game over");
            }
            System.out.println("main --> " + i);
        }
    }
}
