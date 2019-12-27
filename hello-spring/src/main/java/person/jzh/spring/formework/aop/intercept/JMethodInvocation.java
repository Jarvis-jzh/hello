package person.jzh.spring.formework.aop.intercept;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title JMethodInvocation
 * @date 2019/12/10 11:02
 * @description：
 */
public class JMethodInvocation {

    public JMethodInvocation(
            Object proxy, Object target, Method method, Object[] arguments,
            Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers){

    }

    public Object proceed() throws Throwable {
        return null;
    }
}
