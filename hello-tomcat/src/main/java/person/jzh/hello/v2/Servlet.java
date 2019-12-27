package person.jzh.hello.v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author jzh
 * @version 1.0.0
 * @title Servlet
 * @date 2019/12/19 9:38
 * @description：
 */
public interface Servlet {
    void init();
    void service(InputStream is, OutputStream os) throws IOException;
    void destroy();
}
