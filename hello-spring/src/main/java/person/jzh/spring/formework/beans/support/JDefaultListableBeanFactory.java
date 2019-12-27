package person.jzh.spring.formework.beans.support;

import person.jzh.spring.formework.beans.config.JBeanDefinition;
import person.jzh.spring.formework.context.support.JAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jzh
 * @version 1.0.0
 * @title JDefaultListableBeanFactory
 * @date 2019/12/3 9:17
 * @description：
 */
public class JDefaultListableBeanFactory extends JAbstractApplicationContext {
    /**
     * 存储注册信息的beanDefinition
     */
    protected final Map<String, JBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, JBeanDefinition>(256);
}
