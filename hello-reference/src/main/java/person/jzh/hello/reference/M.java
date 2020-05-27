package person.jzh.hello.reference;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/27 22:19
 * @description
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
