package person.jzh.hello.strategy;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 14:04
 * @description
 */
public class Cat implements Comparable<Cat> {
    int weight;
    int height;

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    @Override
    public int compareTo(Cat c) {
        if (this.weight < c.weight)
            return -1;
        else if (this.weight > c.weight)
            return 1;
        else
            return 0;
    }
}
