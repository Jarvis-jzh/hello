## 单例模式（Singleton Pattern）

单例是指确保一个类在任何情况下都绝对只有一个实例，并提供一个全局访问点。

- 隐藏其所有的构造方法
- 只提供一个全局的访问点
- 确保任何情况下都绝对只有一个实例

例：ServletContext、ServletConfig、ApplicationContext、DBPool

### [饿汉式](./src/main/java/person/jzh/hello/singleton/hungry)

```java
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
```

优点：绝对线程安全

缺点：可能造成内存浪费（当应用程序中的单例对象非常多的话，不管你现在有用没用，都进行赋值），能够被反射破坏

### [懒汉式](./src/main/java/person/jzh/hello/singleton/lazy)

```java
public class LazySingleton {
    
    private static LazySingleton instance = null;
    
    private LazySingleton() {}
    
    public static LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

优点：避免了资源的浪费

缺点：有可能出现线程不安全（多线程的情况下，可能会存在两个或以上的线程同时访问，因为对象还没实例化，
所以两个线程都会进入 if 判断并判断为 true ，也就意味着会同时返回两个不同的实例，造成单例被破坏的局面），能够被反射破坏


### [双重检查锁](./src/main/java/person/jzh/hello/singleton/lazy)

```java
public class DoubleCheckSingleton {

    private volatile static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {}

    public static DoubleCheckSingleton getInstance(){
        if (null == instance) {
            synchronized (DoubleCheckSingleton.class) {
                if (null == instance) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
```
优点：避免了资源的浪费，线程安全，保证了性能

缺点：代码的写法不够优雅，可读性差，能够被反射破坏

### [静态内部类](./src/main/java/person/jzh/hello/singleton/lazy)
```java
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton() {}

    public final static LazyStaticInnerClassSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private final static class LazyHolder {
        private static final LazyStaticInnerClassSingleton INSTANCE = new LazyStaticInnerClassSingleton();
    }
}
```
优点：避免了资源的浪费，线程安全，保证了性能，而且优雅

缺点：能够被反射破坏

解决：可以在构造方法上加判断，能够解决反射破坏，但不够优雅
```java
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton() {
        if (LazyHolder.INSTANCE != null) {
            throw new RuntimeException("非法创建单例对象");
        }
    }
    // 略...
}
```



### [枚举](./src/main/java/person/jzh/hello/singleton/register)

```java
public enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance() {
        return INSTANCE;
    }
}
```

优点：即现实了单例，又可以保证代码的优雅，同时实现了线程安全

缺点：不是懒加载



### [内部枚举类](./src/main/java/person/jzh/hello/singleton/register)

```java
public class InnerEnumSingleton {
    private enum SingletonHolder {
        INSTANCE;
        private InnerEnumSingleton singleton;

        SingletonHolder() {
            singleton = new InnerEnumSingleton();
        }

        public InnerEnumSingleton getSingleton() {
            return singleton;
        }
    }
    
    public InnerEnumSingleton getSingleton() {
        return SingletonHolder.INSTANCE.getSingleton();
    }
}
```

优点：实现了懒加载和线程安全

缺点：代码不够优雅



### [容器](./src/main/java/person/jzh/hello/singleton/register)

```java
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getInstance(String className){
        if (ioc.containsKey(className)){
            return ioc.get(className);
        }
        synchronized (ioc) {
            Object obj = null;
            try {
                obj = Class.forName(className).newInstance();
                ioc.put(className, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }
}
```

优点：实现了懒加载和线程安全，同时便于对实例进行管理

场景：String IoC 的实现方式





## 工厂模式（Factory Pattern）

主要是为创建对象提供过渡接口，以便将创建对象的具体过程屏蔽起来，达到提高灵活性的目的。它提供了一种创建对象的最佳方式。在工厂模式中，我们在创建对象时不会对客户端暴露创建逻辑，并且是通过使用一个共同的接口来指向新创建的对象。



### [简单工厂模式](./src/main/java/person/jzh/hello/factory/simple)

又称为静态工厂方法模式，可以根据参数的不同，返回不同类的实例。简单工厂模式专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。



创建接口

```java
public interface Shape {
    void draw();
}
```

创建实现类

```java
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
```

```java
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
```

```java
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
```

创建类型

```java
public enum ShapeType {
    CIRCLE,
    RECTANGLE,
    SQUARE
}
```

创建工厂类

```java
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
```



### [抽象工厂模式](./src/main/java/person/jzh/hello/factory/abstracts)

抽象工厂模式是围绕一个超级工厂创建其他工厂。该超级工厂又称为其他工厂的工厂。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

在抽象工厂模式中，接口是负责创建一个相关对象的工厂，不需要显式指定它们的类。每个生成的工厂都能按照工厂模式提供对象。



