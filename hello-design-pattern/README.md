## 单例模式（Singleton Pattern）

单例是指确保一个类在任何情况下都绝对只有一个实例，并提供一个全局访问点。

- 隐藏其所有的构造方法
- 只提供一个全局的访问点
- 确保任何情况下都绝对只有一个实例

例：ServletContext、ServletConfig、ApplicationContext、DBPool

### 饿汉式

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

### 懒汉式

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


### 双重检查锁

```java
public class DoubleCheckSingleton {

    private static DoubleCheckSingleton instance = null;

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

### 静态内部类
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
