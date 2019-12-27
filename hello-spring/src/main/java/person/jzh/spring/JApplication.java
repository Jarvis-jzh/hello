package person.jzh.spring;

import person.jzh.spring.formework.context.JApplicationContext;

/**
 * @author jzh
 * @version 1.0.0
 * @title Test
 * @date 2019/12/4 12:26
 * @description：
 */
public class JApplication {

    public static void main(String[] args) throws Exception {
        JApplicationContext context = new JApplicationContext("classpath:application.properties");
        Object myAction = context.getBean("myAction");
        System.out.println(myAction);
        System.out.println(context);
//        ClassLoader loader = this.getClass().getClassLoader();
//        System.out.println(loader);
//        InputStream is = loader.getResourceAsStream("application.properties");
//        System.out.println(is);

//        Class<TestDemo> testDemoClass = TestDemo.class;
//        System.out.println("");
    }
}
