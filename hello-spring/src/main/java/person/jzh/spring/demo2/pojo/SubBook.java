package person.jzh.spring.demo2.pojo;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/10 15:27
 * @description
 */
public class SubBook extends Book implements BeanClassLoaderAware,
        EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware,
        ApplicationEventPublisherAware, MessageSourceAware {

    private String bookSystem;

    public String getBookSystem() {
        return bookSystem;
    }

    public void setBookSystem(String bookSystem) {
        System.out.println("设置BookSystem 的属性值");
        this.bookSystem = bookSystem;
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("SubBookClass.setBeanClassLoader() 方法被调用了");
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("SubBookClass.setApplicationEventPublisher() 方法被调用了");
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("SubBookClass.setEmbeddedValueResolver() 方法被调用了");
    }

    public void setEnvironment(Environment environment) {
        System.out.println("SubBookClass.setEnvironment() 方法被调用了");
    }

    public void setMessageSource(MessageSource messageSource) {
        System.out.println("SubBookClass.setMessageSource() 方法被调用了");
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("SubBookClass.setResourceLoader() 方法被调用了");
    }

}
