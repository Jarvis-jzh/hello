package person.jzh.hello.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author jzh
 * @version 1.0.0
 * @title NIOClient
 * @date 2020/1/3 17:52
 * @description：
 */
public class NIOClient {
    SocketChannel client;
    InetSocketAddress serverAddress = new InetSocketAddress("localhost", 9090);
    Selector selector;
    Selector selector1;
    ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
    ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    SelectionKey key = null;

    public NIOClient() throws IOException {
        // 构造 client 实例
        client = SocketChannel.open();
        // 设置为非阻塞模式
        client.configureBlocking(false);
        // 连接服务器
        client.connect(serverAddress);
        // 构造 Selector 实例
        selector = Selector.open();
        // 向管家注册连接事件
        key = client.register(selector, SelectionKey.OP_CONNECT);
    }

    public void chat() throws IOException {
        Scanner in = new Scanner(System.in);
        while (!client.finishConnect()) ;
        write("上线~");
        while (true) {
            String str = in.nextLine();
            write(str);
        }
    }

    public void write(String str) throws IOException {
        sendBuffer.clear();
        sendBuffer.put(str.getBytes());
        sendBuffer.flip();
        client.write(sendBuffer);
    }

    public static void main(String[] args) throws IOException {
        new NIOClient().chat();
    }
}
