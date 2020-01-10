package person.jzh.hello.factory.simple;

import person.jzh.hello.factory.entity.shapes.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title SimpleFactory
 * @date 2020/1/10 15:58
 * @description：
 */
public class ShapeFactory {
    public Shape getShape(ShapeType shapeType){
        switch (shapeType){
            case SQUARE:
                return new Square();
            case CIRCLE:
                return new Circle();
            case RECTANGLE:
                return new Rectangle();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape s1 = factory.getShape(ShapeType.CIRCLE);
        Shape s2 = factory.getShape(ShapeType.SQUARE);
        Shape s3 = factory.getShape(ShapeType.RECTANGLE);

        s1.draw();
        s2.draw();
        s3.draw();
    }
}
