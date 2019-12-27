package person.jzh.hello.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author jzh
 * @version 1.0.0
 * @title NIOServer
 * @date 2019/12/19 17:19
 * @description：
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        // 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 设置channel 为非阻塞方式
        serverSocketChannel.configureBlocking(false);

        // 绑定本机服务器端口号，监听12345端口
        serverSocketChannel.bind(new InetSocketAddress(12345));

        System.out.println("NIO Server Starting, Listening ON Port: " + serverSocketChannel.getLocalAddress());

        // NIO三大剑客：Selector, Channel, Buffer
        // 创建Selector
        Selector selector = Selector.open();

        // 把当前的通道的连接建立事件，注册在Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 创建缓冲区接收数据
        ByteBuffer buffer = ByteBuffer.allocate(2048);

        while (true) {
            // 阻塞
            // 系统调用 select
            int select = selector.select();

            if (select == 0) {
                continue;
            }

            // 有通道已经准备好相关事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 循环所有通道上的事件
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                // 如果是通道的连接事件，则新建连接好r的通道，并将该通道的读事件注册在selector上
                if (key.isAcceptable()) {
                    // 基于serverSocketChannel 连接事件触发之后

                    // socket再一次封装
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();

                    // 创建新的socketChannel(SocketChannel是对Socket的再一次封装)
                    SocketChannel socketChannel = channel.accept();

                    System.out.println("Connection From: " + socketChannel.getRemoteAddress());

                    // 将socketChannel 的读事件，注册到selector上
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                }

                // 如果准备好的事件为读事件
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();

                    // 基于通道将数据读取到缓冲Buffer中
                    channel.read(buffer);
                    String request = new String(buffer.array()).trim();
                    buffer.clear();

                    System.out.println("Receive Request: " + channel.getRemoteAddress() + ", msg: " + request);

                    String response = "Response: " + request + ".\n";

                    // 基于通道返回请求方内容
                    channel.write(ByteBuffer.wrap(response.getBytes()));
                }

                // 移除已经处理过的准备好的事件
                iterator.remove();
            }
        }
   }
}
