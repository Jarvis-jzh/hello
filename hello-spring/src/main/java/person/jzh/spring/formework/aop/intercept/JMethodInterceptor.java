package person.jzh.spring.formework.aop.intercept;

/**
 * @author jzh
 * @version 1.0.0
 * @title JMethodInterceptor
 * @date 2019/12/10 14:30
 * @description：
 */
public interface JMethodInterceptor {

    Object invoke(JMethodInvocation invocation) throws Throwable;
}
