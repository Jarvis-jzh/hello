package person.jzh.spring.formework.context;

import person.jzh.spring.formework.annotation.JAutowired;
import person.jzh.spring.formework.annotation.JController;
import person.jzh.spring.formework.annotation.JService;
import person.jzh.spring.formework.core.JBeanFactory;
import person.jzh.spring.formework.beans.JBeanWrapper;
import person.jzh.spring.formework.beans.config.JBeanDefinition;
import person.jzh.spring.formework.beans.config.JBeanPostProcessor;
import person.jzh.spring.formework.beans.support.JBeanDefinitionReader;
import person.jzh.spring.formework.beans.support.JDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jzh
 * @version 1.0.0
 * @title JApplicationContext
 * @date 2019/12/3 9:05
 * @description：按源码分析的套路，IoC、DI、MVC、AOP
 */
public class JApplicationContext extends JDefaultListableBeanFactory implements JBeanFactory {

    private String[] configLoactions;

    private JBeanDefinitionReader reader;

    /**
     * 单例的IoC容器缓存
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(16);

    private Map<String, JBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, JBeanWrapper>(16);

    // ClassPathXmlApplication
    public JApplicationContext(String... configLoactions) {
        this.configLoactions = configLoactions;
        try {
            refresh();
            System.out.println("JApplicationContext 初始化完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * IoC:
     * 1、定位，定位配置文件
     * 2、加载配置文件，扫描相关的类，把它们封装成BeanDefinition
     * 3、注册，把配置信息放到容器里（伪IoC容器）
     * 4、把不是延时加载的类，要提前初始化
     */
    @Override
    public void refresh() throws Exception {
        // 1、定位，定位配置文件
        reader = new JBeanDefinitionReader(this.configLoactions);
        System.out.println("JBeanDefinitionReader 初始化完成");
        // 2、加载配置文件，扫描相关的类，把它们封装成BeanDefinition
        List<JBeanDefinition> beanDefinions = reader.loadBeanDefinitions();
        System.out.println("loadBeanDefinitions 初始化完成");
        // 3、注册，把配置信息放到容器里（伪IoC容器）
        doRegisterBeanDefinition(beanDefinions);
        System.out.println("doRegisterBeanDefinition 初始化完成");
        // 4、把不是延时加载的类，要提前初始化
        doAutowrited();
        System.out.println("doAutowrited 初始化完成");
    }

    /**
     * 只处理非延时加载的类，有提前初始化
     */
    private void doAutowrited() {
        for (Map.Entry<String, JBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRegisterBeanDefinition(List<JBeanDefinition> beanDefinions) {
        for (JBeanDefinition beanDefinion : beanDefinions) {
            super.beanDefinitionMap.put(beanDefinion.getFactoryBeanName(), beanDefinion);
        }
    }

    /**
     * 装饰器模式
     * 1、保留原来的OOP关系
     * 2、我需要对它进行扩展，增强（为了以后的AOP打基础）
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) throws Exception {
        // System.out.println(beanName);
        // 1、初始化（doCreateBean()）
        Object instance = null;

        // 这里要用工厂模式 + 策略模式（未做                                                        ）
        JBeanPostProcessor processor = new JBeanPostProcessor();
        processor.postProcessBeforeInitialization(instance, beanName);

        instance = instantiateBean(beanName);


        // 3、把这个对象封装到BeanWrapper
        JBeanWrapper beanWrapper = new JBeanWrapper(instance);

        // 2、拿到BeanWrapper之后，把BeanWrapper保存到IoC容器中去
//        if (this.factoryBeanInstanceCache.containsKey(beanName)){
//            throw new Exception("The " + beanName + " is exists!!!");
//        }
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        processor.postProcessAfterInitialization(instance, beanName);

        // 3、注入
        populateBean(beanName, new JBeanDefinition(), beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    public Object getBean(Class<?> clazz) throws Exception {
        return getBean(clazz.getName());
    }

    private Object instantiateBean(String beanName) {
        JBeanDefinition jBeanDefinition = this.beanDefinitionMap.get(beanName);
        // 1、拿到要实例化的对象的类名
        String className = jBeanDefinition.getBeanClassName();

        // 2、反射实例化，得到一个对象
        Object instance = null;
        try {
            // 假设默认就是单例
            if (this.singletonObjects.containsKey(className)) {
                instance = this.singletonObjects.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.singletonObjects.put(className, instance);
                this.singletonObjects.put(jBeanDefinition.getFactoryBeanName(), instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3、把这个对象封装到BeanWrapper
        // JBeanWrapper beanWrapper = new JBeanWrapper(instance);

        // singletonObjects
        // factoryBeanInstanceCache


        // 4、把BeanWrapper存到IOC容器里
        return instance;
    }

    private void populateBean(String beanName, JBeanDefinition jBeanDefinition, JBeanWrapper jBeanWrapper) {
        Object instance = jBeanWrapper.getWrappedInstance();

        // jBeanDefinition.getBeanClassName();

        Class<?> clazz = jBeanWrapper.getWrappedClass();
        // 判断只有加了注解的类，才执行依赖iyty注入
        if (!(clazz.isAnnotationPresent(JController.class) || clazz.isAnnotationPresent(JService.class))) {
            return;
        }

        // 获取所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(JAutowired.class)) {
                continue;
            }
            JAutowired autowired = field.getAnnotation(JAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }
            // 强制访问
            field.setAccessible(true);
            try {
                if (this.factoryBeanInstanceCache.get(autowiredBeanName) == null){
                    continue;
                }
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取所有的Key
     *
     * @return
     */
    public String[] getBeanDefinitionNames(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }
}