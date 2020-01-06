package person.jzh.hello.map.jdk7.v2;

import person.jzh.hello.map.jdk7.MyMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title MyHashMap
 * @date 2020/1/3 14:57
 * @description： JDK 1.7
 */
public class MyHashMapV2<K, V> implements MyMap<K, V> {
    // 定义默认数组大小 16 defaultAddSizeFactor = useSize / defaultLenth   4 / 16 = 0.25
    private static int defaultLenth = 1 << 4;

    // 扩容标准所使用的 useSize / 数组长度 > 0.75
    // defaultAddSizeFactor 过大，造成扩容概率变低，存储小，但是就是 存和取 效率降低
    // 0.9有限的数组长度空间内会形成链表在存或者取值都需要进行大量的遍历和判断（逻辑）
    private static double defaultAddSizeFactor = 0.75;

    // 记录数组位置的使用（加1代表有一个位置被使用）
    // 使用数组位置的总是可见性的操作，将不会存在处理器特定内存当中，这些内存是其他线程中不可见的
    private volatile int useSize;

    private int size;

    // 定义Map 骨架数组之一
    private Entry<K, V>[] table = null;

    // spring 门面模式运用
    public MyHashMapV2() {
        this(defaultLenth, defaultAddSizeFactor);
    }

    public MyHashMapV2(int length, double defaultAddSizeFactor) {
        if (length < 0) {
            throw new IllegalArgumentException("参数不能为负数" + length);
        }
        if (defaultAddSizeFactor <= 0 || Double.isNaN(defaultAddSizeFactor)) {
            throw new IllegalArgumentException("扩容标准必须是大于0的数字" + defaultAddSizeFactor);
        }
        this.defaultLenth = length;
        this.defaultAddSizeFactor = defaultAddSizeFactor;
        // 内存当中划分连续的内存空间
        table = new Entry[defaultLenth];
    }


    @Override
    public V put(K k, V v) {
        // 判断 是否需要扩容
        if (useSize > defaultAddSizeFactor * defaultLenth) {
            up2Size();
        }
        // 获取数组位置的方法
        int index = getIndex(k, table.length);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            // Entry：存储在数组和链表当中的数据结构对象
            table[index] = new Entry(k, v, null);
            useSize++;
        } else if (entry != null) {
            table[index] = new Entry(k, v, entry);
        }
        size++;
        return table[index].getValue();
    }

    // 定位：寻找存或者取位置
    private int getIndex(K k, int length) {
        int m = length - 1;
        int index = hash(k.hashCode()) & m;
        return index;
    }

    // 要求1：具备自己的hash算法
    // JDK 大量数学运算之后，确定的三元右移运算
    private int hash(int hashCode) {
        hashCode = hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 12));
        return hashCode ^ ((hashCode >>> 7) ^ (hashCode >>> 4));
    }

    // 保证自我判断扩容（数组）
    private void up2Size() {
        List<Entry<K, V>> entryList = new ArrayList<>();
        // for出来后就代表内容全部遍历到 entryList 当中
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            // 说明数组上这个位置的 Entry = table[i] != null
            foundEntryByNext(table[i], entryList);
        }

        // 设置 entryList
        if (entryList.size() > 0) {
            size = 0;
            useSize = 0;
            defaultLenth = defaultLenth << 1;
            table = new Entry[defaultLenth];
            for (Entry<K, V> entry : entryList) {
                // 将所有的链表打断
                if (entry.next != null) {
                    entry.next = null;
                }
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    // 这个方法里面不存在我们的 entry == null 的情况
    private void foundEntryByNext(Entry<K, V> entry, List<Entry<K, V>> entryList) {
        if (entry.next != null) {
            entryList.add(entry);
            // 递归，不断的一层一层存取 entry
            foundEntryByNext(entry.next, entryList);
        } else {
            // 没有链表的情况
            entryList.add(entry);
        }

    }

    @Override
    public V get(K k) {
        int index = getIndex(k, table.length);
        if (table[index] == null) {
            throw new NullPointerException();
        }
        // key 存在情况
        return findValueByEqualKey(k, table[index]);
    }

    // 不同 k 可能在同一个位置
    private V findValueByEqualKey(K k, Entry<K, V> entry) {
        if (k == entry.getKey() || k.equals(entry.getKey())){
            return entry.getValue();
        } else if (entry.next != null) {
            // 循环一层一层递归，和传进来的 k 相同的 entry
            return findValueByEqualKey(k, entry.next);
        }
        return null;
    }

    // 创建一个内部存储的对象类型
    class Entry<K, V> implements MyMap.Entry<K, V> {
        K k;
        V v;
        // 指向被 this 挤压下去的 Entry 对象
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }
    }

//    public int getUseSize(){
//        return this.useSize;
//    }

    public int length(){
        return table.length;
    }

    public int size() {
        return this.size;
    }
}
