package person.jzh.spring.formework.context;

/**
 * @author jzh
 * @version 1.0.0
 * @title JApplicationContextAware
 * @date 2019/12/3 9:37
 * @description：
 * 通过解耦的方式获取IoC容器的顶层设计
 * 后面将通过一个监听器去扫描所有的类，只要实现了此接口
 * 将自动调用setApplicationContext（）方法，从而将IoC容器注入到目标类中（观察者模式）
 */
public interface JApplicationContextAware {
    void setApplicationContext(JApplicationContext applicationContext);
}
