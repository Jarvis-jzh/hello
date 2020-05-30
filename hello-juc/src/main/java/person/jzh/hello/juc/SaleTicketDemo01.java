package person.jzh.hello.juc;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/28 11:57
 * @description 简单的卖票例子
 * 真正的多线程开发，公司中的开发
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 1、属性、方法
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发：多线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();

        new Thread(()->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(()->{}, "C").start();
    }
}

/**
 * 资源类 OOP
 */
class Ticket {
    // 属性、方法
    private int number = 50;

    public void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票，还剩余" + number);
        }
    }
}
