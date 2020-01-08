package person.jzh.hello.singleton.lazy;

/**
 * @author jzh
 * @version 1.0.0
 * @title LazySingleton
 * @date 2020/1/6 10:36
 * @description： 静态内部类
 */
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton() {
        if (LazyHolder.INSTANCE != null) {
            throw new RuntimeException("非法创建单例对象");
        }
    }

    public final static LazyStaticInnerClassSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private final static class LazyHolder {
        private static final LazyStaticInnerClassSingleton INSTANCE = new LazyStaticInnerClassSingleton();
    }
}
