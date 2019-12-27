package person.jzh.hello.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author jzh
 * @version 1.0.0
 * @title RequestHandler
 * @date 2019/12/19 17:03
 * @description：
 */
public class RequestHandler extends Thread {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader br = null;

        try {
            System.out.println("Connection from: " + socket.getRemoteSocketAddress());

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String request = br.readLine();

                System.out.println("Receive Request : " + socket.getRemoteSocketAddress() + ", msg : " + request);

                String response = "Response: " + request + ".\n";

                socket.getOutputStream().write(response.getBytes());

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
