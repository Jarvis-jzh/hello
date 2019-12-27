package person.jzh.spring.formework.aop;

/**
 * @author jzh
 * @version 1.0.0
 * @title JAopProxy
 * @date 2019/12/10 10:48
 * @description：
 */
public interface JAopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
