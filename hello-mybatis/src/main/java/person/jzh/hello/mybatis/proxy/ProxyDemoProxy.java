package person.jzh.hello.mybatis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jzh
 * @version 1.0.0
 * @title ProxyDemoProxy
 * @date 2019/12/27 15:39
 * @description：
 */
public class ProxyDemoProxy implements InvocationHandler {

    private IProxyInterface target;

    public ProxyDemoProxy(IProxyInterface target) {
        this.target = target;
    }

//    public Object getInstance(IProxyInterface target){
//        this.target = target;
//        Class<?> clazz = this.target.getClass();
//        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
//    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy before -> " + method.getName());
//        System.out.println(proxy.toString());
        Object invoke = method.invoke(target, args);
        return invoke;
    }
}
