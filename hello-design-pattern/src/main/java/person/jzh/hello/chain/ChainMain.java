package person.jzh.hello.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:06
 * @description 责任链主方法
 */
public class ChainMain {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:), <script>, 欢迎访问 www.jzh.plus 996");

        // demo1(msg);
        // demo2(msg);
        // demo3(msg);
        demo4(msg);

        System.out.println(msg);
    }

    public static void demo1(Msg msg) {
        // 处理 msg
        new HTMLFilter().doFilter(msg);
        new SensitiveFilter().doFilter(msg);
    }

    public static void demo2(Msg msg) {
        List<Filter> filters = new ArrayList<>();
        filters.add(new HTMLFilter());
        filters.add(new SensitiveFilter());
        for (Filter f : filters) {
            f.doFilter(msg);
        }
    }

    public static void demo3(Msg msg) {
        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter())
                .add(new SensitiveFilter());
        fc.doFilter(msg);
    }

    public static void demo4(Msg msg) {
        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter())
                .add(new SensitiveFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new FaceFilter())
                .add(new URLFilter());

        fc.add(fc2);

        fc.doFilter(msg);
    }
}
