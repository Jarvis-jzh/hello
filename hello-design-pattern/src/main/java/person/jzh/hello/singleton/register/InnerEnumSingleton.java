package person.jzh.hello.singleton.register;

/**
 * @author jzh
 * @version 1.0.0
 * @title InnerEnumSingleton
 * @date 2020/1/10 16:19
 * @description： 内部枚举类实现单例
 */
public class InnerEnumSingleton {
    private enum SingletonHolder {
        INSTANCE;
        private InnerEnumSingleton singleton;

        SingletonHolder() {
            singleton = new InnerEnumSingleton();
        }

        public InnerEnumSingleton getSingleton() {
            return singleton;
        }
    }

    public InnerEnumSingleton getSingleton() {
        return SingletonHolder.INSTANCE.getSingleton();
    }
}
