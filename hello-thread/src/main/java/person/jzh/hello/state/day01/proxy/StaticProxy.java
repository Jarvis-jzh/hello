package person.jzh.hello.proxy;

/**
 * @author jzh
 * @version 1.0.0
 * @title StaticProxy
 * @date 2019/12/11 16:25
 * @description：
 * 静态代理
 * 接口：
 * 1、真实角色
 * 2、代理角色
 */
public class StaticProxy {
    public static void main(String[] args) {
        // new Thread(线程对象).start();
        new WeddingCompany(new You()).happyMarry();
    }
}
interface Marry {
    void happyMarry();
}
class You implements Marry {

    @Override
    public void happyMarry() {
        System.out.println("你和嫦娥终于奔月了");
    }
}

// 代理角色
class WeddingCompany implements Marry {
    private Marry target;
    public WeddingCompany(Marry target){
        this.target = target;
    }

    @Override
    public void happyMarry() {
        ready();
        this.target.happyMarry();
        after();
    }

    private void after() {
        System.out.println("闹玉兔");
    }

    private void ready() {
        System.out.println("布置场地");
    }
}
