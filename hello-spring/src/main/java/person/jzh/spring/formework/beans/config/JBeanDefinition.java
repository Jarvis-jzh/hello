package person.jzh.spring.formework.beans.config;

import lombok.Data;

/**
 * @author jzh
 * @version 1.0.0
 * @title JBeanDefinition
 * @date 2019/12/3 9:26
 * @description：
 */
@Data
public class JBeanDefinition {

    /**
     * bean类名
     */
    private String beanClassName;

    /**
     * 默认不懒加载
     */
    private boolean lazyInit = false;

    /**
     * bean全类名
     */
    private String factoryBeanName;

    private boolean singleton = true;
}
