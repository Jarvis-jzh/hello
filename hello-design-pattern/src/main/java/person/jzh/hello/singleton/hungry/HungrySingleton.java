package person.jzh.hello.singleton.hungry;

/**
 * @author jzh
 * @version 1.0.0
 * @title hungrySiingleton
 * @date 2020/1/6 10:36
 * @description： 饿汉式
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
