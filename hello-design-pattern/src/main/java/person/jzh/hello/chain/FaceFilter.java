package person.jzh.hello.chain;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:29
 * @description
 */
public class FaceFilter implements Filter {
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace(":)", "^v^");
        m.setMsg(r);
        return true;
    }
}
