package person.jzh.hello.factory.entity.colors;

/**
 * @author jzh
 * @version 1.0.0
 * @title Green
 * @date 2020/1/10 17:45
 * @description：
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::file() method.");
    }
}
