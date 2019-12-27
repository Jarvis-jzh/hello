package person.jzh.spring.formework.aop.support;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title JAdvisedSupport
 * @date 2019/12/10 10:53
 * @description：
 */
public class JAdvisedSupport {

    private Class<?> targetClass;

    public Class<?> getTargetClass(){
        return this.targetClass;
    }

    public Object getTarge(){
        return null;
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass){
        return null;
    }
}
