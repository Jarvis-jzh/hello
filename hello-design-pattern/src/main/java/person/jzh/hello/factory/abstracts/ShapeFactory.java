package person.jzh.hello.factory.abstracts;

import person.jzh.hello.factory.entity.colors.Color;
import person.jzh.hello.factory.entity.colors.ColorType;
import person.jzh.hello.factory.entity.shapes.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title ShapeFactory
 * @date 2020/1/10 17:47
 * @description：
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(ColorType type) {
        return null;
    }

    @Override
    public Shape getShape(ShapeType type) {
        switch (type){
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
}
