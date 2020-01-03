package person.jzh.hello.syn.day03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title HappyCinema
 * @date 2019/12/16 11:18
 * @description：快乐影院
 */
public class HappyCinema02 {
    public static void main(String[] args) {
        // 可用的位置
        List<Integer> available = new ArrayList<>();
        available.add(1);
        available.add(2);
        available.add(3);
        available.add(4);
        available.add(6);
        available.add(7);

        List<Integer> seats1 = new ArrayList<>();
        seats1.add(1);
        seats1.add(2);
        List<Integer> seats2 = new ArrayList<>();
        seats2.add(3);
        seats2.add(4);
        seats2.add(6);

        SxtCinema c = new SxtCinema(available, "happy sxt");
        new Thread(new HappyCustomer(c, seats1), "老高").start();
        new Thread(new HappyCustomer(c, seats2), "老裴").start();
    }
}

// 顾客
class HappyCustomer implements Runnable {
    SxtCinema cinema;

    List<Integer> seats;

    public HappyCustomer(SxtCinema cinema, List<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (cinema) {
            boolean flag = cinema.bookTickets(seats);
            if (flag) {
                System.out.println("出票成功 --> " + Thread.currentThread().getName() + "，位置为：" + seats);
            } else {
                System.out.println("出票失败 --> " + Thread.currentThread().getName() + "，位置不够");
            }
        }
    }
}

// 影院
class SxtCinema {
    // 可用的位置
    List<Integer> available;
    // 名称
    String name;

    public SxtCinema(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }

    // 购票
    public boolean bookTickets(List<Integer> seats) {
        System.out.println("欢迎" + this.name + "，可用位置为：" + available);
        List<Integer> copy = new ArrayList<>();
        copy.addAll(available);

        // 相减
        copy.removeAll(seats);
        // 判断大小
        if (available.size() - copy.size() != seats.size()) {
            return false;
        }
        // 成功
        available = copy;
        return true;
    }
}
