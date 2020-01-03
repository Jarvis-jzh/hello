package person.jzh.hello.syn.day03;

/**
 * @author jzh
 * @version 1.0.0
 * @title Happy12306
 * @date 2019/12/16 14:43
 * @description：
 */
public class Happy12306 {
    public static void main(String[] args) {
        Web12396 c = new Web12396(4, "happy sxt");
        new Passenger(c, "老高", 2).start();
        new Passenger(c, "老裴", 2).start();
    }
}

// 顾客
class Passenger extends Thread {
    int seats;

    public Passenger(Runnable target, String name, int seats) {
        super(target, name);
        this.seats = seats;
    }
}

// 火车票网
class Web12396 implements Runnable {
    // 可用的位置
    int available;
    // 名称
    String name;

    public Web12396(int available, String name) {
        this.available = available;
        this.name = name;
    }

    // 购票
    public synchronized boolean bookTickets(int seats) {
        System.out.println("可用位置为：" + available);
        if (seats > available) {
            return false;
        }
        available -= seats;
        return true;
    }

    @Override
    public void run() {
        Passenger p = (Passenger) Thread.currentThread();
        boolean flag = this.bookTickets(p.seats);
        if (flag) {
            System.out.println("出票成功 --> " + Thread.currentThread().getName() + "，位置为：" + p.seats);
        } else {
            System.out.println("出票失败 --> " + Thread.currentThread().getName() + "，位置不够");
        }
    }
}