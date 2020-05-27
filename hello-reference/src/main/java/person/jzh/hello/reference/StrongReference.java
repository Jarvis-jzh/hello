package person.jzh.hello.reference;

import java.io.IOException;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/27 22:16
 * @description 强引用
 */
public class StrongReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();

        System.in.read();
    }
}
