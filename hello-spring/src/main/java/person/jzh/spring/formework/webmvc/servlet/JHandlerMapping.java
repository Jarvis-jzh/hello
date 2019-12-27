package person.jzh.spring.formework.webmvc.servlet;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * @author jzh
 * @version 1.0.0
 * @title JHandlerMapping
 * @date 2019/12/4 17:54
 * @description：
 */
public class JHandlerMapping {
    // 保存方法对应的实例
    private Object controller;
    // 保存映射的方法
    private Method method;
    // URL的正则匹配
    private Pattern pattern;

    public JHandlerMapping(Pattern pattern, Object controller, Method method) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean equals(Object obj) {
//        if (this.getMethod().getName().compareTo(((JHandlerMapping) obj).getMethod().getName()) == 0){
//            return true;
//        }
//        return super.equals(obj);
        return this.getMethod().getName().equals(((JHandlerMapping) obj).getMethod().getName());
    }
}
