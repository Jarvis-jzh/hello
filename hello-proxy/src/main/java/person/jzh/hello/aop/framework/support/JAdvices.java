package person.jzh.hello.aop.framework.support;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author jzh
 * @version 1.0.0
 * @title JAdvices
 * @date 2019/12/21 14:31
 * @description：
 */
@Data
public class JAdvices {

    private Object instance;

    private Method method;

    private String throwName;

    public JAdvices(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }
}
