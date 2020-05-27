package person.jzh.hello.chain;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:30
 * @description
 */
public class URLFilter implements Filter {
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace("www.jzh.plus", "https://www.jzh.plus");
        m.setMsg(r);
        return true;
    }
}
