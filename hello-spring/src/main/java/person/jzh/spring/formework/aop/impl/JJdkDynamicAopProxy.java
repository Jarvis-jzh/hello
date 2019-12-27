package person.jzh.spring.formework.aop.impl;

import person.jzh.spring.formework.aop.JAopProxy;
import person.jzh.spring.formework.aop.intercept.JMethodInvocation;
import person.jzh.spring.formework.aop.support.JAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title JJdkDynamicAopProxy
 * @date 2019/12/10 10:50
 * @description：
 */
public class JJdkDynamicAopProxy implements JAopProxy, InvocationHandler {

    private JAdvisedSupport advised;

    public JJdkDynamicAopProxy(JAdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advised.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.advised.getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> interceptorsAndDynamicMethodMatchers = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, this.advised.getTargetClass());
        JMethodInvocation invocation = new JMethodInvocation(proxy, null, method, args, this.advised.getTargetClass(), interceptorsAndDynamicMethodMatchers);
        return invocation.proceed();
    }
}
