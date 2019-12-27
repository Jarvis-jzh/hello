package person.jzh.hello.io.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jzh
 * @version 1.0.0
 * @title BIOServerByThread
 * @date 2019/12/19 17:03
 * @description：
 */
public class BIOServerByThread {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(8888);
        System.out.println("BIO Server Starting, port: " + socket.getLocalPort());
        while (true) {
            Socket accept = socket.accept();
            System.out.println("Connection from: " + accept.getRemoteSocketAddress());
            new RequestHandler(accept).start();
        }
    }
}
