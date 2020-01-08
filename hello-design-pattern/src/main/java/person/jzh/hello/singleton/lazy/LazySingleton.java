package person.jzh.hello.singleton.lazy;

/**
 * @author jzh
 * @version 1.0.0
 * @title LazySingleton
 * @date 2020/1/6 10:36
 * @description： 懒汉式
 */
public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
