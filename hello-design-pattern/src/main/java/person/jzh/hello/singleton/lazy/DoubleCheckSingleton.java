package person.jzh.hello.singleton.lazy;

/**
 * @author jzh
 * @version 1.0.0
 * @title DoubleCheckSingleton
 * @date 2020/1/6 17:15
 * @description：
 */
public class DoubleCheckSingleton {

    private static DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {}

    public static DoubleCheckSingleton getInstance(){
        if (null == instance) {
            synchronized (DoubleCheckSingleton.class) {
                if (null == instance) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
