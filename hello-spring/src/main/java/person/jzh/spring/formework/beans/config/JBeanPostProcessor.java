package person.jzh.spring.formework.beans.config;

/**
 * @author jzh
 * @version 1.0.0
 * @title JBeanPostProcessor
 * @date 2019/12/4 16:58
 * @description：
 */
public class JBeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
