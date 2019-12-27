package person.jzh.hello.proxy.dynamicproxy.Jdkproxy;

import person.jzh.hello.proxy.Person;
import person.jzh.hello.proxy.dynamicproxy.jproxy.JInvocationHandler;
import person.jzh.hello.proxy.dynamicproxy.jproxy.JProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jzh
 * @version 1.0.0
 * @title JDKMeipo
 * @date 2019/12/21 8:54
 * @description：
 */
public class JDKMeipo implements InvocationHandler {

    private Object target;

    public Object getInstance(Person person){
        this.target = person;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.target, args);
        after();
        return obj;
    }

    private void before() {
        System.out.println("我是媒婆，我要给你找对象，现在已经确认你的需求");
        System.out.println("开始物色");
    }

    private void after() {
        System.out.println("ok的话，准备办事");
    }
}
