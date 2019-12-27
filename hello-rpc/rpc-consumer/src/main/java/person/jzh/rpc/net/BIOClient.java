package person.jzh.rpc.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author jzh
 * @version 1.0.0
 * @title BIOClient
 * @date 2019/12/20 17:04
 * @description：
 */
public class BIOClient {
    public static Object callRemoteService(Object reqObj, String host, int port){
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Object obj = null;
        try {
            Socket socket = new Socket(host, port);

            oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(reqObj);

            oos.flush();

            ois = new ObjectInputStream(socket.getInputStream());

            // 获取请求的内容
            obj = ois.readObject();


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
        }

        return obj;
    }
}
