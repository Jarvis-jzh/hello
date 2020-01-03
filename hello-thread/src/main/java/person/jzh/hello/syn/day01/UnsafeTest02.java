package person.jzh.hello.syn.day01;

/**
 * @author jzh
 * @version 1.0.0
 * @title UnsafeTest02
 * @date 2019/12/16 8:27
 * @description：线程不安全：取钱
 */
public class UnsafeTest02 {
    public static void main(String[] args) {
        // 账户
        Account account = new Account(100, "礼金");
        Drawing you = new Drawing(account, 80, "可悲的你");
        Drawing wife = new Drawing(account, 90, "happy的她");
        you.start();
        wife.start();
    }
}
// 账户
class Account {

    int money;

    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}
class Drawing extends Thread {
    Account account;
    // 取的钱数
    int drawingMoney;
    // 口袋的总数
    int packetTotal;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }

    private void test() {
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