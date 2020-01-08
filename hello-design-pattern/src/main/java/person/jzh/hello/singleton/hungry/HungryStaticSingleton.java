package person.jzh.hello.singleton.hungry;

/**
 * @author jzh
 * @version 1.0.0
 * @title HungryStaticSingleton
 * @date 2020/1/6 10:43
 * @description：
 */
public class HungryStaticSingleton {

    private static final HungryStaticSingleton instance;

    static {
        instance = new HungryStaticSingleton();
    }

    private HungryStaticSingleton() {}

    public static HungryStaticSingleton getInstance(){
        return instance;
    }
}
