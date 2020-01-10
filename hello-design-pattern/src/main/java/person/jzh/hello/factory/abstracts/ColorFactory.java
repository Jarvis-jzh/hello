package person.jzh.hello.factory.abstracts;

import person.jzh.hello.factory.entity.colors.*;
import person.jzh.hello.factory.entity.shapes.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title ColorFactory
 * @date 2020/1/10 17:58
 * @description：
 */
public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(ColorType type) {
        switch (type){
            case RED:
                return new Red();
            case BLUE:
                return new Blue();
            case GREEN:
                return new Green();
            default:
                return null;
        }
    }

    @Override
    public Shape getShape(ShapeType type) {
        return null;
    }
}
