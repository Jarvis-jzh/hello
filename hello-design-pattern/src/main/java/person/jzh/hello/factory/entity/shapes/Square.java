package person.jzh.hello.factory.entity.shapes;

/**
 * @author jzh
 * @version 1.0.0
 * @title Square
 * @date 2020/1/10 17:17
 * @description：
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
