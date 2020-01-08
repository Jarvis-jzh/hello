package person.jzh.hello.singleton.register;

/**
 * @author jzh
 * @version 1.0.0
 * @title EnumSingleton
 * @date 2020/1/6 18:00
 * @description：
 */
public enum EnumSingleton {
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
