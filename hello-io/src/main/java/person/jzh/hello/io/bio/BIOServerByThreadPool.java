package person.jzh.hello.io.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jzh
 * @version 1.0.0
 * @title BIOServerByThreadPool
 * @date 2019/12/19 17:11
 * @description：
 */
public class BIOServerByThreadPool {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        ServerSocket socket = new ServerSocket(9999);
        System.out.println("BIO Server Starting, port: " + socket.getLocalPort());
        while (true) {
            Socket accept = socket.accept();
            System.out.println("Connection from: " + accept.getRemoteSocketAddress());
            executorService.execute(new RequestHandler(accept));
        }
    }
}
