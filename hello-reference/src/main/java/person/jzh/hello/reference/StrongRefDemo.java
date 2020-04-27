package person.jzh.hello.reference;

import java.io.PrintStream;

public class StrongRefDemo
{

    public StrongRefDemo()
    {
    }

    public static void main(String args[])
    {
        demo1();
        demo2();
    }

    public static void demo1()
    {
        System.out.println("demo1");
        Object obj = strongRef;
        strongRef = null;
        System.gc();
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF1A").append(obj).toString());
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF1A").append(strongRef).toString());
    }

    public static void demo2()
    {
        System.out.println("demo2");
        Object strongRef2 = new Object();
        Object obj = strongRef2;
        strongRef2 = null;
        System.gc();
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF1A").append(obj).toString());
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF1A").append(strongRef2).toString());
    }

    static Object strongRef = new Object();

}
