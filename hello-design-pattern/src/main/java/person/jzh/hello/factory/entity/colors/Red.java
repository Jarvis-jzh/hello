package person.jzh.hello.factory.entity.colors;

/**
 * @author jzh
 * @version 1.0.0
 * @title Red
 * @date 2020/1/10 17:44
 * @description：
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::file() method.");
    }
}
