package person.jzh.hello.v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author jzh
 * @version 1.0.0
 * @title BBServlet
 * @date 2019/12/19 9:42
 * @description：
 */
public class BBServlet implements Servlet {
    @Override
    public void init() {
        System.out.println("bb init");
    }

    @Override
    public void service(InputStream is, OutputStream os) throws IOException {
        System.out.println("bb service");
        os.write("i am bb servlet".getBytes());
        os.flush();
    }

    @Override
    public void destroy() {

    }
}
