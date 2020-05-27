package person.jzh.spring.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import person.jzh.spring.demo2.pojo.Book;
import person.jzh.spring.demo2.pojo.SubBook;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/10 15:26
 * @description
 */
public class Demo2Application {
    public static void main(String[] args) throws InterruptedException {
        // 为面试而准备的Bean生命周期加载过程
//        ApplicationContext context = new ClassPathXmlApplicationContext("Bean-Lifecycle.xml");
//        Book book = (Book)context.getBean("book");
//        System.out.println("Book name = " + book.getBookName());
//        ((ClassPathXmlApplicationContext) context).destroy();

        // 完整的加载过程，当然了解的越多越好
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SubBean-Lifecycle.xml");
        SubBook subBook = (SubBook) applicationContext.getBean("bookClass");
        System.out.println("BookSystemName = " + subBook.getBookSystem());
        ((ClassPathXmlApplicationContext) applicationContext).registerShutdownHook();
    }
}
