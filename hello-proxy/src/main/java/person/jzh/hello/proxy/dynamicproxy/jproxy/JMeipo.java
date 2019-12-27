package person.jzh.hello.proxy.dynamicproxy.jproxy;

import java.lang.reflect.Method;

/**
 * @author jzh
 * @version 1.0.0
 * @title JMeipo
 * @date 2019/12/21 16:54
 * @description：
 */
public class JMeipo implements JInvocationHandler {

    private Object target;

    public Object getInstance(Object person) throws Exception {
        this.target = person;
        Class<?> clazz = target.getClass();
        return JProxy.newProxyInstance(new JClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
