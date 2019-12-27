package person.jzh.spring.formework.beans;

/**
 * @author jzh
 * @version 1.0.0
 * @title JBeanWrapper
 * @date 2019/12/3 14:36
 * @description：
 */
public class JBeanWrapper {

    private Object wrappedInstance;

    private Class<?> wrappedClass;

    public JBeanWrapper(Object instance) {
        this.wrappedInstance = instance;
        this.wrappedClass = instance.getClass();
    }

    public Object getWrappedInstance(){
        return this.wrappedInstance;
    }

    public Class<?> getWrappedClass(){
        return this.wrappedClass;
    }
}
