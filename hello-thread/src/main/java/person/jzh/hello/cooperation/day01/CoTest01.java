package person.jzh.hello.cooperation.day01;

/**
 * @author jzh
 * @version 1.0.0
 * @title CoTest01
 * @date 2019/12/16 16:01
 * @description：协作模型：生产者消费者实现方式一：管程法
 * 借助缓冲区
 */
public class CoTest01 {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Productor(container).start();
        new Consumer(container).start();
    }
}

// 生产者
class Productor extends Thread {
    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        // 生产
        for (int i = 0; i < 100; i++) {
            System.out.println("生产->" + i + "个馒头");
            container.push(new Steamedbun(i));
        }
    }
}

// 消费者
class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        // 消费
        for (int i = 0; i < 100; i++) {
            System.out.println("消费->" + container.pop().id + "个馒头");
        }
    }
}

// 缓冲区
class SynContainer {
    // 存储容器
    Steamedbun[] buns = new Steamedbun[10];

    // 计数器
    int count = 0;

    // 存储(生产)
    public synchronized void push(Steamedbun bun) {
        // 何时能生产，容器存在空间
        // 不能生产
        if (count == buns.length){
            try {
                // 线程阻塞，消费者通知生产时解除
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 存在空间可以生产
        buns[count] = bun;
        count++;
        // 存在数据了，可以通知消费了
        this.notifyAll();
    }

    // 获取(消费)
    public synchronized Steamedbun pop() {
        // 何时消费，容器中是否存在数据
        // 没有数据只有等待
        if (count == 0){
            try {
                // 线程阻塞，生产者通知消费解除阻塞
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        Steamedbun bun = buns[count];
        // 存在空间了，可以唤醒对方生产
        this.notifyAll();
        return bun;
    }

}

// 馒头
class Steamedbun {
    int id;

    public Steamedbun(int id) {
        this.id = id;
    }
}
