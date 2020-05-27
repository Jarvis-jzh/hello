package person.jzh.hello.reference;

import java.io.PrintStream;
import java.lang.ref.SoftReference;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/27 22:54
 * @description 软引用：只有内存不足时才会被回收
 */
public class SoftRefDemo {

    public static void main(String args[])
            throws InterruptedException {
//        demo1();
        demo2();
    }

    public static void demo1() {
        Object softRef = new Object();
        SoftReference softReference = new SoftReference(softRef);
        softRef = null;
        System.out.println((new StringBuilder()).append("GC 之前：").append(softReference.get()).toString());
        System.out.println((new StringBuilder()).append("GC 之前：").append(softRef).toString());
        System.gc();
        System.out.println((new StringBuilder()).append("GC 之后：").append(softReference.get()).toString());
        System.out.println((new StringBuilder()).append("GC 之后：").append(softRef).toString());
        softReference = null;
        System.out.println();
        System.out.println((new StringBuilder()).append("null 之后：").append(softReference.get()).toString());
        System.out.println((new StringBuilder()).append("null 之后：").append(softRef).toString());
    }

    /**
     * 设置 JVM 参数： -Xmx23M
     */
    public static void demo2() {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m.get());

        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get());
    }
}
