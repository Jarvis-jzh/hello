package person.jzh.rpc.factory;

import java.lang.reflect.Proxy;

/**
 * @author jzh
 * @version 1.0.0
 * @title ProxyFactory
 * @date 2019/12/20 16:40
 * @description：
 */
public class ProxyFactory {
    public static <T> T getService(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(),
                new Class[]{
                        clazz
                }, new RPCInvocationHandler());
    }
}
