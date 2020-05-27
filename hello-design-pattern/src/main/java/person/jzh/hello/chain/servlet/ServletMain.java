package person.jzh.hello.chain.servlet;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:56
 * @description
 */
public class ServletMain {
    public static void main(String[] args) {
        Request req = new Request();
        req.setMsg("大家好:), <script>, 欢迎访问 www.jzh.plus 996");
        Response resp = new Response();
        resp.setMsg("");

        FilterChain chain = new FilterChain();
        chain.add(new HTMLFilter())
                .add(new SensitiveFilter());
        chain.doFilter(req, resp, chain);
        System.out.println(req);
        System.out.println(resp);
    }
}
