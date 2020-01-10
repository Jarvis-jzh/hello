package person.jzh.hello.singleton.register;

/**
 * @author jzh
 * @version 1.0.0
 * @title EnumSingleton
 * @date 2020/1/6 18:00
 * @description： 枚举实现单例
 */
public enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance() {
        return INSTANCE;
    }
}
