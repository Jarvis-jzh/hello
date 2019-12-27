package person.jzh.hello.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jzh
 * @version 1.0.0
 * @title BIOServer
 * @date 2019/12/19 16:46
 * @description：
 */
public class BIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(7777);
        System.out.println("BIO Server Starting, port: " + socket.getLocalPort());
        BufferedReader br = null;
        while (true) {
            Socket accept = socket.accept();
            try {
                System.out.println("Connection from: " + accept.getRemoteSocketAddress());

                br = new BufferedReader(new InputStreamReader(accept.getInputStream()));

                while (true) {
                    String request = br.readLine();

                    System.out.println("Receive Request : " + accept.getRemoteSocketAddress() + ", msg : " + request);

                    String response = "Response: " + request + ".\n";

                    accept.getOutputStream().write(response.getBytes());

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != socket) {
                    socket.close();
                }
                if (null != accept) {
                    accept.close();
                }
                if (null != br) {
                    br.close();
                }
            }
        }
    }
}
