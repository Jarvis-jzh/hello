package person.jzh.hello.chain.servlet;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:57
 * @description
 */
public class Response {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
