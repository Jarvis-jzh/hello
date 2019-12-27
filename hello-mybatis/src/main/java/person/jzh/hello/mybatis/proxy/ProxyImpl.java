package person.jzh.hello.mybatis.proxy;

/**
 * @author jzh
 * @version 1.0.0
 * @title ProxyImpl
 * @date 2019/12/27 15:38
 * @description：
 */
public class ProxyImpl implements IProxyInterface {
    @Override
    public void hello() {
        System.out.println("ProxyImpl hello1");
    }

    @Override
    public void hello2() {
        System.out.println("ProxyImpl hello2");
    }
}
