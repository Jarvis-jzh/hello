package person.jzh.hello.reference;

import java.io.PrintStream;
import java.lang.ref.SoftReference;

public class SoftRefDemo
{

    public SoftRefDemo()
    {
    }

    public static void main(String args[])
        throws InterruptedException
    {
        Object softRef = new Object();
        SoftReference softReference = new SoftReference(softRef);
        softRef = null;
        System.out.println((new StringBuilder()).append("GC \u4E4B\u524D\uFF1A").append(softReference.get()).toString());
        System.out.println((new StringBuilder()).append("GC \u4E4B\u524D\uFF1A").append(softRef).toString());
        System.gc();
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF1A").append(softReference.get()).toString());
        System.out.println((new StringBuilder()).append("GC \u4E4B\u540E\uFF1A").append(softRef).toString());
        softReference = null;
        System.out.println((new StringBuilder()).append("null \u4E4B\u540E\uFF1A").append(softReference.get()).toString());
        System.out.println((new StringBuilder()).append("null \u4E4B\u540E\uFF1A").append(softRef).toString());
    }
}
