package person.jzh.spring.formework.context.support;

/**
 * @author jzh
 * @version 1.0.0
 * @title JAbstractApplicationContext
 * @date 2019/12/3 9:08
 * @description：IoC容器实现的顶层设计
 */
public abstract class JAbstractApplicationContext {
    /**
     * 受保护，只提供给子类重写（最少知道原则）
     */
    public void refresh() throws Exception {}
}