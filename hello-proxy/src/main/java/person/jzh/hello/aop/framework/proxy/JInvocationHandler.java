package person.jzh.hello.aop.framework.proxy;

import java.lang.reflect.Method;

/**
 * @author jzh
 * @version 1.0.0
 * @title JInvocationHandler
 * @date 2019/12/21 10:36
 * @description：
 */
public interface JInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
