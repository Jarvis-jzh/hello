package person.jzh.hello.syn.day02;

/**
 * @author jzh
 * @version 1.0.0
 * @title SynBlockTest01
 * @date 2019/12/16 10:39
 * @description：
 */
public class SynBlockTest01 {
    public static void main(String[] args) {
        // 账户
        SynBlockAccount account = new SynBlockAccount(900, "礼金");
        SynBlockDrawing you = new SynBlockDrawing(account, 80, "可悲的你");
        SynBlockDrawing wife = new SynBlockDrawing(account, 90, "happy的她");
        you.start();
        wife.start();
    }
}

// 账户
class SynBlockAccount {

    int money;

    String name;

    public SynBlockAccount(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class SynBlockDrawing extends Thread {
    SynBlockAccount account;
    // 取的钱数
    int drawingMoney;
    // 口袋的总数
    int packetTotal;

    public SynBlockDrawing(SynBlockAccount account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }

    // 目标锁定account
    private void test() {
        if (account.money <= 0){
            return;
        }
        // 同步块
        synchronized(account){
            if (account.money < drawingMoney){
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -= drawingMoney;
            packetTotal += drawingMoney;
            System.out.println(this.getName() + " --> 账户余额为：" + account.money);
            System.out.println(this.getName() + " --> 口袋的钱为：" + packetTotal);
        }
    }
}
