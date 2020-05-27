package person.jzh.hello.strategy;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 14:14
 * @description
 */
public class Dog implements Comparable<Dog> {

    int food;

    public Dog(int food) {
        this.food = food;
    }

    @Override
    public int compareTo(Dog d) {
        if (this.food < d.food)
            return -1;
        else if (this.food > d.food)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
