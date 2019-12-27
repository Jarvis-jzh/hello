package person.jzh.hello.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jzh
 * @version 1.0.0
 * @title TestServer
 * @date 2019/12/19 8:49
 * @description：
 */
public class TestServer {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(8888);
        Socket accept = socket.accept();
        InputStream is = accept.getInputStream();
        OutputStream os = accept.getOutputStream();

//        int i = is.read();
//        while (i != -1){
//            System.out.print((char) i);
//            i = is.read();
//        }

        System.out.println("--------------------------------------------");

        os.write("HTTP/1.1 200 OK\n".getBytes());
        os.write("Content-Type:text/html;charset=utf-8\n".getBytes());
        os.write("Server:Apache-Coyote/1.1\n".getBytes());
        os.write("\n\n".getBytes());

        System.out.println("--------------------------------------------");

        StringBuffer buf = new StringBuffer();
        buf.append("<html>")
                .append("<head><title>我是标题</title><head>")
                .append("<body>")
//                .append("<h1>花木成畦手自栽</h1>")
                .append("</body>")
                .append("</html>");
        System.out.println("--------------------------------------------");
        os.write(buf.toString().getBytes());
        os.flush();
        System.out.println("--------------------------------------------");
        if (null != is){
            is.close();
        }
        if (null != os){
            os.close();
        }
        if (null != socket){
            socket.close();
        }
        if (null != accept){
            accept.close();
        }
    }
}
