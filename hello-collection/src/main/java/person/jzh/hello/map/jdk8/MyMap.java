package person.jzh.hello.map.jdk8;

/**
 * @author jzh
 * @version 1.0.0
 * @title MyMap
 * @date 2020/1/4 16:10
 * @description：
 */
public interface MyMap<K, V> {
    // MyMap基本功能是 快速存
    V put(K k, V v);

    // 快速取
    V get(K k);

    // 用于测试扩充
    int length();

    int size();

    // 定义一个内部接口
    interface Entry<K, V> {
        K getKey();
        V getValue();
        V setValue(V newV);
    }
}
