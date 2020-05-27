package person.jzh.hello.strategy;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 14:27
 * @description
 */
public interface Comparator<T> {
    int compare(T o1, T o2);
}
