package person.jzh.hello.reference;

import java.io.PrintStream;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomRefDemo
{

    public PhantomRefDemo()
    {
    }

    public static void main(String args[])
        throws InterruptedException
    {
        ReferenceQueue queue = new ReferenceQueue();
        Object phantomObj = new Object();
        PhantomReference phantomReference = new PhantomReference(phantomObj, queue);
        System.out.println(phantomReference.get());
        phantomObj = null;
        System.out.println((new StringBuilder()).append("GC \u524D\uFF1A").append(phantomReference.get()).toString());
        System.out.println((new StringBuilder()).append("GC \u524D\uFF1A").append(queue.poll()).toString());
        System.gc();
        System.out.println((new StringBuilder()).append("GC \u540E\uFF1A").append(phantomReference.get()).toString());
        System.out.println((new StringBuilder()).append("GC \u540E\uFF1A").append(queue.poll()).toString());
        Thread.sleep(200L);
        System.out.println((new StringBuilder()).append("\u7761\u7720\u540E\uFF1A").append(phantomReference.get()).toString());
        System.out.println((new StringBuilder()).append("\u7761\u7720\u540E\uFF1A").append(queue.poll()).toString());
    }
}
