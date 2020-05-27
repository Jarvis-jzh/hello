package person.jzh.hello.reference;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/27 22:24
 * @description 强引用
 */
public class StrongRefDemo {

    public static void main(String args[]) {
        demo1();
        demo2();
    }

    public static void demo1() {
        System.out.println("demo1");
        Object obj = strongRef;
        strongRef = null;
        System.gc();
        System.out.println((new StringBuilder()).append("GC 之后").append(obj).toString());
        System.out.println((new StringBuilder()).append("GC 之后").append(strongRef).toString());
    }

    public static void demo2() {
        System.out.println("demo2");
        Object strongRef2 = new Object();
        Object obj = strongRef2;
        strongRef2 = null;
        System.gc();
        System.out.println((new StringBuilder()).append("GC 之后").append(obj).toString());
        System.out.println((new StringBuilder()).append("GC 之后").append(strongRef2).toString());
    }

    static Object strongRef = new Object();

}
