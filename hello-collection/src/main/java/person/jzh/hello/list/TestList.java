package person.jzh.hello.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title TestList
 * @date 2019/12/20 9:18
 * @description：
 * 测试Collection接口中的方法
 */
public class TestList {
    public static void main(String[] args) {
        test3();
    }

    private static void test3() {
        List<String > list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        System.out.println(list);

        list.add(2, "E");
        System.out.println(list);

        list.remove(2);
        System.out.println(list);

        list.set(2, "F");
        System.out.println(list);
    }

    public static void test1() {
        Collection<String> c = new ArrayList<>();
        System.out.println(c.isEmpty());
        c.add("1eqwe");

        System.out.println(c.size());
        System.out.println(c);

        c.remove("1eqwe");
        System.out.println(c);
    }

    public static void test2() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);

        list2.add(3);
        list2.add(4);
        list2.add(5);

        // list1.removeAll(list2);
        // list1.addAll(list2);
        list1.retainAll(list2);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list1.containsAll(list2));
    }
}
