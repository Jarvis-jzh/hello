package person.jzh.hello.syn.day03;

/**
 * @author jzh
 * @version 1.0.0
 * @title HappyCinema
 * @date 2019/12/16 11:18
 * @description：快乐影院
 */
public class HappyCinema01 {
    public static void main(String[] args) {
        Cinema c = new Cinema(2, "happy sxt");
        new Thread(new Customer(c, 2), "老高").start();
        new Thread(new Customer(c, 1), "老裴").start();
    }
}

// 顾客
class Customer implements Runnable {
    Cinema cinema;

    int seats;

    public Customer(Cinema cinema, int seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (cinema){
            boolean flag = cinema.bookTickets(seats);
            if (flag) {
                System.out.println("出票成功 --> " + Thread.currentThread().getName() + "，位置为：" + seats);
            }else{
                System.out.println("出票失败 --> " + Thread.currentThread().getName() + "，位置不够");
            }
        }
    }
}

// 影院
class Cinema {
    // 可用的位置
    int available;
    // 名称
    String name;

    public Cinema(int available, String name) {
        this.available = available;
        this.name = name;
    }

    // 购票
    public boolean bookTickets(int seats) {
        System.out.println("可用位置为：" + available);
        if (seats > available) {
            return false;
        }
        available -= seats;
        return true;
    }
}
