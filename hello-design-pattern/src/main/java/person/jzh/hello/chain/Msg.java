package person.jzh.hello.chain;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:07
 * @description
 */
public class Msg {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
