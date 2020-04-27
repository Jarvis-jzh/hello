package person.jzh.hello.reference;

import java.io.PrintStream;
import java.lang.ref.WeakReference;

public class WeakRefDemo
{

    public WeakRefDemo()
    {
    }

    public static void main(String args[])
    {
        demo1();
        System.out.println("------------------------");
        demo2();
        System.out.println("------------------------");
        demo3();
        System.out.println("------------------------");
        demo4();
    }

    public static void demo1()
    {
        Object weakObj = new Object();
        WeakReference weakReference = new WeakReference(weakObj);
        System.gc();
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF0Cnull \u4E4B\u524D\uFF1A").append(weakReference.get()).toString());
        weakObj = null;
        System.out.println((new StringBuilder()).append("GC \u4E4B\u524D\uFF0Cnull \u4E4B\u540E\uFF1A").append(weakReference.get()).toString());
        System.gc();
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF0Cnull \u4E4B\u540E\uFF1A").append(weakReference.get()).toString());
    }

    public static void demo2()
    {
        String str = "hello weak ref";
        WeakReference weakReference = new WeakReference(str);
        str = null;
        System.out.println((new StringBuilder()).append("GC \u4E4B\u524D\uFF1A").append(weakReference.get()).toString());
        System.gc();
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF1A").append(weakReference.get()).toString());
    }

    public static void demo3()
    {
        String strObj = new String("hello weak ref");
        WeakReference weakReference = new WeakReference(strObj);
        strObj = null;
        System.out.println((new StringBuilder()).append("GC \u4E4B\u524D\uFF1A").append(weakReference.get()).toString());
        System.gc();
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF1A").append(weakReference.get()).toString());
    }

    public static void demo4()
    {
        String str = "hello weak ref";
        String strObj = new String("hello weak ref");
        String strObj2 = new String(str);
        String strObj3 = strObj;
        String str3 = str;
        System.out.println(str == strObj);
        System.out.println(strObj == strObj2);
        System.out.println(strObj2 == str);
        System.out.println(strObj3 == strObj);
        System.out.println(str3 == str);
    }
}
