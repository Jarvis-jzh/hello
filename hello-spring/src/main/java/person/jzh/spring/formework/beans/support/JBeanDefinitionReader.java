package person.jzh.spring.formework.beans.support;

import com.sun.org.apache.bcel.internal.generic.RET;
import person.jzh.spring.formework.beans.config.JBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author jzh
 * @version 1.0.0
 * @title JBeanDefinitionReader
 * @date 2019/12/3 10:25
 * @description：
 */
public class JBeanDefinitionReader {

    private List<String> registyBeanClasses = new ArrayList<String>(16);

    private Properties config = new Properties();

    /**
     * 固定配置文件中的Key，相当于XML的规范
     */
    private final String SCAN_PACKAGE = "scanPackage";

    public JBeanDefinitionReader(String... locations) {
        // 通过URL定位找到其所对应的文件，然后转换为文件流读取
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        //转换为文件路径，实际上就是把.替换为/就OK了
        // this.getClass()
        // this.getClass().getClassLoader()
        URL url = this.getClass().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                registyBeanClasses.add(className);
            }
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    /**
     * 把配置文件中扫描的所有的配置信息转换为JBeanDefinition对象，以便于之后IoC操作方便
     *
     * @return
     */
    public List<JBeanDefinition> loadBeanDefinitions() {
        List<JBeanDefinition> result = new ArrayList<JBeanDefinition>(16);

        try {
            for (String className : registyBeanClasses) {
                Class<?> beanClass = Class.forName(className);

                // 如果是一个接口，是不实例化的
                // 用它实现类来实例
                if (beanClass.isInterface()){
                    continue;
                }

                // beanName有三种情况：
                // 1、默认是类名首字母小写
                // 2、自定义名字
                // 3、接口注入
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));
                result.add(doCreateBeanDefinition(beanClass.getName(), beanClass.getName()));


                // JBeanDefinition beanDefinition = doCreateBeanDefinition(className);
                Class<?> [] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖
                    //为什么？因为Spring没那么智能，就是这么傻
                    //这个时候，可以自定义名字
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    //把每一个配信息解析成一个BeanDefinition
    private JBeanDefinition doCreateBeanDefinition(String factoryBeanName,String beanClassName){
        JBeanDefinition beanDefinition = new JBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    /**
     * 把每一个配置信息解析成一个BeanDefinition
     *
     * @param className
     * @return
     */
    private JBeanDefinition doCreateBeanDefinition(String className) {
        try {
            Class<?> beanClass = Class.forName(className);
            if (beanClass.isInterface()) {
                return null;
            }
            JBeanDefinition beanDefinition = new JBeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(beanClass.getSimpleName());
            return beanDefinition;

            // 这些Bean有可能是一个接口，不能实例化，只能对它的实现类进行处理
            // 但现在这个地方还没做初始化，我们只是把配置文件包装
            // 如果是接口的知，用它的实现类作为BeanClassName


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //如果类名本身是小写字母，确实会出问题
    //但是我要说明的是：这个方法是我自己用，private的
    //传值也是自己传，类也都遵循了驼峰命名法
    //默认传入的值，存在首字母小写的情况，也不可能出现非字母的情况

    //为了简化程序逻辑，就不做其他判断了，大家了解就OK
    //其实用写注释的时间都能够把逻辑写完了
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //之所以加，是因为大小写字母的ASCII码相差32，
        // 而且大写字母的ASCII码要小于小写字母的ASCII码
        //在Java中，对char做算学运算，实际上就是对ASCII码做算学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
