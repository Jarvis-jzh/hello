package person.jzh.hello.chain.servlet;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 17:09
 * @description
 */
public class HTMLFilter implements Filter {
    @Override
    public boolean doFilter(Request req, Response resp, FilterChain chain) {
        req.setMsg(req.getMsg().replaceAll("<", "[").replaceAll(">", "]") + " -- HTMLFilter");;
        chain.doFilter(req, resp, chain);
        resp.setMsg(resp.getMsg() + " -- HTMLFilter");
        return false;
    }
}
