package person.jzh.rpc.net;

import person.jzh.rpc.dispatch.ServiceDispatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author jzh
 * @version 1.0.0
 * @title RPCThreadProcessor
 * @date 2019/12/20 16:06
 * @description：
 */
public class RPCThreadProcessor implements Runnable {

    private Socket socket;

    public RPCThreadProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());

            // 获取请求的内容
            Object obj = ois.readObject();

            // 服务的调用
            Object resObj = ServiceDispatch.dispatch(obj);

            oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(resObj);

            oos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
