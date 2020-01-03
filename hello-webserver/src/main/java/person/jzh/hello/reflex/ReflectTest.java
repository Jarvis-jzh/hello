package person.jzh.hello.reflex;

/**
 * @author jzh
 * @version 1.0.0
 * @title ReflectTest
 * @date 2020/1/2 19:45
 * @description：
 * 反射：把Java类中的各种结构（方法、属性、构造器、类名）映射成一个个的Java对象
 * 1、获取 Class 对象
 */
public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获取 Class 对象的三种方式
        IPhone iPhone = new IPhone();
        // 1、对象.getClass()
        Class clz = iPhone.getClass();
        // 2、类.class
        clz = IPhone.class;
        // 3、Class.forName("包名.类名")
        clz = Class.forName("person.jzh.hello.reflex.IPhone");

    }
}

class IPhone{
    public IPhone() {

    }
}
