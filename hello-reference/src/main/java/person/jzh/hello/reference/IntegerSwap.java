package person.jzh.hello.reference;

import java.lang.reflect.Field;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/27 22:27
 * @description 写一个方法用来交换两个 Integer 对象的值
 */
public class IntegerSwap {

    public static void main(String args[])
            throws NoSuchFieldException, IllegalAccessException {
        System.out.println(new Object());
        Integer a = Integer.valueOf(1);
        Integer b = Integer.valueOf(2);
        System.out.println((new StringBuilder()).append("before: a = ").append(a).append(", b = ").append(b).toString());
        swap(a, b);
        System.out.println((new StringBuilder()).append("after: a = ").append(a).append(", b = ").append(b).toString());
    }

    public static void swap(Integer i1, Integer i2)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        Integer tmp = new Integer(i1.intValue());
        System.out.println(tmp == i1);
        field.set(i1, i2);
        field.set(i2, tmp);
        System.out.println(Integer.valueOf(1));
        System.out.println(Integer.valueOf(2));
    }
}
