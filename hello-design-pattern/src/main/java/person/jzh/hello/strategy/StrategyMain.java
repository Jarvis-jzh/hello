package person.jzh.hello.strategy;

import java.util.Arrays;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 14:07
 * @description
 */
public class StrategyMain {
    public static void main(String[] args) {
        // Cat[] a = {new Cat(3, 3), new Cat(5, 5), new Cat(1, 1)};

        Dog[] a = {new Dog(3), new Dog(5), new Dog(1)};;
        Sorter<Dog> sorter = new Sorter<>();
        sorter.sort(a, (o1, o2) -> {
            if (o1.food < o2.food)
                return -1;
            else if (o1.food > o2.food)
                return 1;
            else
                return 0;
        });
        System.out.println(Arrays.toString(a));
    }
}
