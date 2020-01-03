package person.jzh.hello.map;

import person.jzh.hello.map.v1.MyHashMap;
import person.jzh.hello.map.v2.MyHashMapV2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jzh
 * @version 1.0.0
 * @title TestHash
 * @date 2020/1/3 9:27
 * @description：
 */
public class TestHash {
    public static void main(String[] args) {
        test4();
    }

    public static void test4() {
        MyMap<String, String> map = new MyHashMapV2<>();
        for (int i = 0; i < 1000; i++) {
            map.put("jzh" + i, "value" + i);
        }
        System.out.println(map.length());
        System.out.println(map.size());
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("key:" + "jzh" + i + "  " + map.get("jzh" + i));
//        }
    }

    public static void test3() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put("jzh" + i, "value" + i);
            System.out.println(map.size());;
        }
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("key:" + "jzh" + i + "  " + map.get("jzh" + i));
//        }
    }

    public static void test2() {
        MyMap<String, String> map = new MyHashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put("jzh" + i, "value" + i);
            System.out.println(map.length());
        }
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("key:" + "jzh" + i + "  " + map.get("jzh" + i));
//        }
    }

    public static void test1() {
        HashMap<String, String> map = new HashMap<>(16);
        String str = "1";
        map.put(str, str);
        map.get(str);
        int h = str.hashCode();
        System.out.println(h >>> 16);
        System.out.println(h ^ (h >>> 16));
    }
}
