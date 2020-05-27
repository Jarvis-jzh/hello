package person.jzh.hello.chain;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:13
 * @description
 */
public class HTMLFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace('<', '[');
        r = r.replace('>', ']');
        msg.setMsg(r);
        return true;
    }
}
