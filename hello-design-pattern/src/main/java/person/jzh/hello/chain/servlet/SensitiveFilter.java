package person.jzh.hello.chain.servlet;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 17:11
 * @description
 */
public class SensitiveFilter implements Filter {
    @Override
    public boolean doFilter(Request req, Response resp, FilterChain chain) {
        req.setMsg(req.getMsg().replaceAll(":\\)", "\\^v\\^") + " -- SensitiveFilter");
        chain.doFilter(req, resp, chain);
        resp.setMsg(resp.getMsg() + " -- SensitiveFilter");
        return true;
    }
}
