package person.jzh.hello.mybatis.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jzh
 * @version 1.0.0
 * @title Test
 * @date 2019/12/27 15:41
 * @description：
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ProxyImpl target = new ProxyImpl();
        IProxyInterface proxyInterface = (IProxyInterface) Proxy.newProxyInstance(
                IProxyInterface.class.getClassLoader(),
                new Class[] {IProxyInterface.class}, new ProxyDemoProxy(target)
        );
        proxyInterface.hello();
    }
}
