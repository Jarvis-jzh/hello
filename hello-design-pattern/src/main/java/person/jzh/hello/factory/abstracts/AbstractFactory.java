package person.jzh.hello.factory.abstracts;

import person.jzh.hello.factory.entity.colors.Color;
import person.jzh.hello.factory.entity.colors.ColorType;
import person.jzh.hello.factory.entity.shapes.Shape;
import person.jzh.hello.factory.entity.shapes.ShapeType;

/**
 * @author jzh
 * @version 1.0.0
 * @title AbstractFactory
 * @date 2020/1/10 17:40
 * @description：
 */
public abstract class AbstractFactory {
    public abstract Color getColor(ColorType type);
    public abstract Shape getShape(ShapeType type);
}
