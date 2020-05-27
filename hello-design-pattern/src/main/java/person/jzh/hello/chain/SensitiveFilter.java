package person.jzh.hello.chain;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:14
 * @description 敏感词处理
 */
public class SensitiveFilter implements Filter {
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace("996", "955");
        m.setMsg(r);
        return false;
    }
}
