package person.jzh.hello.v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author jzh
 * @version 1.0.0
 * @title AAServlet
 * @date 2019/12/19 9:40
 * @description：
 */
public class AAServlet implements Servlet {
    @Override
    public void init() {
        System.out.println("aa init");
    }

    @Override
    public void service(InputStream is, OutputStream os) throws IOException {
        System.out.println("aa service");
        os.write("i am aa servlet".getBytes());
        os.flush();
    }

    @Override
    public void destroy() {

    }
}
