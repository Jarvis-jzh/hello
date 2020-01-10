package person.jzh.hello.factory.entity.shapes;

/**
 * @author jzh
 * @version 1.0.0
 * @title Circle
 * @date 2020/1/10 17:18
 * @description：
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
