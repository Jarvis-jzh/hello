package person.jzh.spring.formework.core;

/**
 * @author jzh
 * @version 1.0.0
 * @title JBeanFactory
 * @date 2019/12/3 8:50
 * @description：单例工厂的顶层设计
 */
public interface JBeanFactory {
    /**
     * 通过它唯一的入口，从IoC容器获取唯一的对象（单例）
     * 根据beanName从IoC容器中获取一个实例Bean
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName) throws Exception;

    Object getBean(Class<?> beanClass) throws Exception;
}
