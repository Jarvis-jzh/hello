package person.jzh.hello.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Hello world!
 */
public class TestClient {
    public static void main(String[] args) {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            socket = new Socket("www.itcast.com", 80);
            is = socket.getInputStream();
            os = socket.getOutputStream();

            os.write("GET /subject/about/index.html HTTP/1.1\n".getBytes());
            os.write("HOST:www.itcast.cn\n".getBytes());
            os.write("\n".getBytes());



            int i = is.read();
            while (i != -1){
                System.out.print((char) i);
                i = is.read();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (null != is){
                    is.close();
                }
                if (null != os){
                    os.close();
                }
                if (null != socket){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendStaticResource(OutputStream os) {

    }

    private static void parse(InputStream is) {

    }
}
