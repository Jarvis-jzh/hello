package person.jzh.hello.map.jdk8.v1;


import person.jzh.hello.map.jdk8.MyMap;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author jzh
 * @version 1.0.0
 * @title MyHashMap
 * @date 2020/1/4 14:40
 * @description：
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    // 默认初始化大小
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    // 下一个要调整数组大小的值
    int threshold;

    // 自增阀值
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // 允许th自定义阀值
    final float loadFactor;

    // 当链表数据量达到8时，转红黑树
    static final int TREEIFY_THRESHOLD = 8;

    // 当红黑树数据量达到6时，转链表
    static final int UNTREEIFY_THRESHOLD = 6;

    // 数组最大的长度
    static final int MAXIMUM_CAPACITY = 1 << 30;

    private Node<K, V>[] table;

    public MyHashMap() {
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        // 初始化长度不小于0
        if (initialCapacity < 0)
            throw new IllegalArgumentException("非法的参数 initialCapacity：" + initialCapacity);

        // 初始化长度大于最大值时，将它更改为最大值
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;

        // 因子（阀值）不能小于等于0
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("非法的参数 loadFactor：" + loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    // 对数组长度进行纠正，必须是2^n
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


    @Override
    public V put(K k, V v) {
        return putVal(hash(k), k, v);
    }

    private V putVal(int hash, K k, V v) {
        Node<K, V>[] tab = table;
        int index, len;
        if (tab == null || (len = tab.length) == 0){
            // 如果table没有初始化，则进行初始化
            tab = resize();
        }
        return null;
    }

    private Node<K, V>[] resize() {
        Node<K, V>[] oldTab = table;
        // 当前数组长度
        int oldCap = oldTab == null ? 0 : oldTab.length;
        int oldThr = threshold;

        int newCap, newThr;
        // 如果长度大于0，则应该是扩容
        if (oldCap > 0){

        } else if (oldThr > 0) {
            // 如果 oldCap = 0 的情况下，即可能没有初始化，则判断用户是否设置了初始化长度，即 threshold > 0
            newCap = oldThr;
        } else {
            // 即没初始化数组，又没自定义数组长度，则用默认长度
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        }

        return new Node[threshold];
    }

    final static int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    @Override
    public Object get(Object o) {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    static class Node<K, V> implements Entry<K, V>{

        final int hash;
        final K k;
        V v;
        Node<K, V> next;

        public Node(K k, V v, int hash, Node<K, V> next){
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.k;
        }

        @Override
        public V getValue() {
            return this.v;
        }

        public V setValue(V newV) {
            V oldV = this.v;
            this.v = newV;
            return oldV;
        }

    }

    // 红黑树
    static class TreeNode<K, V> {

    }
}
