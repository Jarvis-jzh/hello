package person.jzh.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import person.jzh.spring.demo1.service.IModifyService;
import person.jzh.spring.demo1.service.impl.ModifyService;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/2 15:25
 * @description
 */
public class SpringApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IModifyService iModifyService = applicationContext.getBean("modifyService", ModifyService.class);
        String edit = iModifyService.edit(1, "1");
        System.out.println(edit);
    }
}
