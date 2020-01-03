package person.jzh.hello.others.day04;

/**
 * @author jzh
 * @version 1.0.0
 * @title DoubleCheckedLocking
 * @date 2019/12/16 18:22
 * @description：DCL单例模式：懒汉式基础上加入并发控制，在多线程环境下，对外存在一个对象
 * 1、构造器私有化->避免外部new构造器
 * 2、提供私有的静态属性->存储对象的地址
 * 3、提供公共的静态方法->获取属性
 */
public class DoubleCheckedLocking {

    // 2、提供私有的静态属性（这里直接new一个对象叫饿汉式）
    // 没有volatile其它线程可能访问一个没有初始化的对象
    private static volatile DoubleCheckedLocking instance;

    // 1、构造器私有化->避免外部new构造器
    private DoubleCheckedLocking() {

    }

    // 3、提供公共的静态方法
    public static DoubleCheckedLocking getInstance() {
        // 再次检测
        if (null != instance) {
            return instance;
        }
        synchronized (DoubleCheckedLocking.class) {
            if (null == instance) {
                instance = new DoubleCheckedLocking();
                // 1、开辟空间
                // 2、初始化对象信息
                // 3、返回对象的地址给引用
            }
        }
        return instance;
    }

    public static DoubleCheckedLocking getInstance1(long time) {
        if (null == instance) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new DoubleCheckedLocking();
            // 1、开辟空间
            // 2、初始化对象信息
            // 3、返回对象的地址给引用
        }
        return instance;
    }

    public static void main(String[] args) {
//        DoubleCheckedLocking a = DoubleCheckedLocking.getInstance();
//        DoubleCheckedLocking b = DoubleCheckedLocking.getInstance();
//        DoubleCheckedLocking c = DoubleCheckedLocking.getInstance();
//        System.out.println(a + " ---- " + b + " ---- " + c);
        Thread t = new Thread(() -> {
            System.out.println(DoubleCheckedLocking.getInstance());
        });
        t.start();
        System.out.println(DoubleCheckedLocking.getInstance());
//        Thread t = new Thread(() -> {
//            System.out.println(DoubleCheckedLocking.getInstance1(500));
//        });
//        t.start();
//        System.out.println(DoubleCheckedLocking.getInstance1(1000));
    }

}
