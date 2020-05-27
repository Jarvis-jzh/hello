package person.jzh.hello.chain.servlet;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:57
 * @description
 */
public interface Filter {
    boolean doFilter(Request req, Response resp, FilterChain chain);
}
