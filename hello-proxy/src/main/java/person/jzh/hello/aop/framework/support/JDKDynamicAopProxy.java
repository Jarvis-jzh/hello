package person.jzh.hello.aop.framework.support;

import person.jzh.hello.aop.framework.proxy.JClassLoader;
import person.jzh.hello.aop.framework.proxy.JInvocationHandler;
import person.jzh.hello.aop.framework.proxy.JProxy;
import person.jzh.hello.aop.framework.support.JAdvices;
import person.jzh.hello.aop.framework.support.JAdvisedSupport;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author jzh
 * @version 1.0.0
 * @title JDKDynamicAopProxy
 * @date 2019/12/21 14:26
 * @description：
 */
public class JDKDynamicAopProxy implements JInvocationHandler {

    private JAdvisedSupport advised;

    public JDKDynamicAopProxy(JAdvisedSupport config) {
        this.advised = config;
    }

    public Object getProxy() {
        return getProxy(this.advised.getClassLoader());
    }

    public Object getProxy(JClassLoader classLoader) {
        return JProxy.newProxyInstance(classLoader, this.advised.getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, JAdvices> advices = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, this.advised.getTargetClass());
        invokeAdvice(advices.get("before"));
        Object returnValue = null;
        try {
            returnValue = method.invoke(this.advised.getTarget(), args);
        } catch (Exception e){
            invokeAdvice(advices.get("afterThrow"));
            e.printStackTrace();
        }
        invokeAdvice(advices.get("after"));
        return returnValue;
    }

    private void invokeAdvice(JAdvices advices) {
        Object instance = advices.getInstance();
        Method method = advices.getMethod();
        try {
            method.invoke(instance, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
