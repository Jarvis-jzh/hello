package person.jzh.hello.proxy.staticproxy;

/**
 * @author jzh
 * @version 1.0.0
 * @title FatherProxyTest
 * @date 2019/12/21 16:09
 * @description： 静态代理
 */
public class FatherProxyTest {
    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();
    }
}
