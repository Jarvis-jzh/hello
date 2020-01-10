package person.jzh.hello.factory.entity.shapes;

/**
 * @author jzh
 * @version 1.0.0
 * @title Rectangle
 * @date 2020/1/10 17:16
 * @description：
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
