package person.jzh.rpc.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jzh
 * @version 1.0.0
 * @title BIOServer
 * @date 2019/12/20 15:59
 * @description：
 */
public class BIOServer {

    private static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void startServer(int port) throws Exception {
        ServerSocket ss = new ServerSocket(port);

        while (true) {
            // 阻塞的等到客户端的连接到来
            Socket socket = ss.accept();

            // 流的操作，是典型的BIO的处理方式
            // 阻塞，与线程是一个强绑定的关系

            // 多线程的方式，但不能让它无止尽的创建与销毁，所以我们需要用到线程池
            executorService.submit(new RPCThreadProcessor(socket));
        }
    }
}
