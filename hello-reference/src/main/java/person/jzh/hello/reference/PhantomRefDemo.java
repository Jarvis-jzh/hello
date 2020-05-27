package person.jzh.hello.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/27 22:26
 * @description 虚引用：无论是否被回收，都无法直接获取，一般用来管理直接内存
 */
public class PhantomRefDemo {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String args[])
            throws InterruptedException {
        // demo1();
        demo2();
    }

    public static void demo1() throws InterruptedException {
        ReferenceQueue queue = new ReferenceQueue();
        Object phantomObj = new Object();
        PhantomReference phantomReference = new PhantomReference(phantomObj, queue);
        System.out.println(phantomReference.get());
        phantomObj = null;
        System.out.println((new StringBuilder()).append("GC 前").append(phantomReference.get()).toString());
        System.out.println((new StringBuilder()).append("GC 前").append(queue.poll()).toString());
        System.gc();
        System.out.println((new StringBuilder()).append("GC 后").append(phantomReference.get()).toString());
        System.out.println((new StringBuilder()).append("GC 后").append(queue.poll()).toString());
        Thread.sleep(200L);
        System.out.println((new StringBuilder()).append("睡眠后").append(phantomReference.get()).toString());
        System.out.println((new StringBuilder()).append("睡眠后").append(queue.poll()).toString());
    }

    public static void demo2() {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);
        // 无法直接获取
        System.out.println(phantomReference.get());

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while(true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("---  虚引用对象被 Jvm 回收了  ---" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
