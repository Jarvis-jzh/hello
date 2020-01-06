package person.jzh.hello.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title NIOServer
 * @date 2020/1/3 16:40
 * @description： Demo:接收玩家上线的服务器
 */
public class NIOServer {
    // 搭建基础的组件
    // 服务接收客户端对象的位置
    private ServerSocketChannel server;

    int port = 9090;

    // 多路复用注册器，服务端的自身状态，客户的状态
    private Selector selector;

    // 缓冲区（池）
    ByteBuffer recBuffer = ByteBuffer.allocate(1024);
    ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    // 客户端纪录编号
    private static int clientId = 19095;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 消息缓存队列，存放客户端发送给服务端的消息队列
    Map<SelectionKey, String> sessionMsg = new HashMap<>();

    // 记录客户端编号
    Map<SelectionKey, Integer> clientIDMsg = new HashMap<>();

    public NIOServer(int port) throws IOException {
        // 初始化组件
        this.port = port;
        server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(this.port));
        // 这里设置通信模式变为非阻塞
        server.configureBlocking(false);
        // 工厂方法快速拿到实例
        selector = Selector.open();
        // 以上步骤做完，代表服务器初始化完成，告诉给 Selector 为可接收状态
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("【系统消息提示】NIO消息服务器初始化完毕，监听端口：" + this.port);
    }

    // 根据思路，服务端内部启动一个线程去不断的监听 Selector 上面有没有通信事件
    private void listener() throws IOException {
        while (true) {
            // select() 去查询 Selector 有多少事件满足四种类型，返回 int
            int eventCount = selector.select();
            if (eventCount == 0) {
                continue;
            }

            // 满足三种状态事件 SelectionKey 的集合 SelectionKey
            Set<SelectionKey> keys = selector.selectedKeys();
            // SelectionKey：包含客户端的通道信息，且标识这个客户端处于通信当中的哪个事件
            final Iterator<SelectionKey> iteratorKeys = keys.iterator();
            while (iteratorKeys.hasNext()) {
                // 驱动处理模块的抽象
                process(iteratorKeys.next());
                // 移除当前的事件状态
                iteratorKeys.remove();
            }
        }
    }

    // 驱动整个业务读写
    private void process(SelectionKey key) {
        // 连接客户端、读取客户端信息、写信息到客户端的循环
        SocketChannel client = null;
        try {
            // 连接客户端，判断有效判断
            if (key.isValid() && key.isAcceptable()) {
                // 连接服务
                client = server.accept();
                // 代表新的客户端接入
                ++clientId;
                client.configureBlocking(false);
                //告诉服务器你可以读取我带来的信息
                client.register(selector, SelectionKey.OP_READ);

                System.out.println("【系统消息提示】客户端线程：" + clientId + " 已经上线了~");
            } else if (key.isValid() && key.isReadable()) {
                // 读客户端信息
                // 服务端从 SocketChannel 读取客户端发送过来的信息
                recBuffer.clear();
                client = (SocketChannel) key.channel();

                // 读取进来大量的字节信息
                int len = client.read(recBuffer);
                if (len > 0) {
                    String msg = new String(recBuffer.array(), 0, len);
                    // 服务器当中的消息缓存
                    sessionMsg.put(key, msg);
                    System.out.println("当前处理线程ID：" + Thread.currentThread().getId() + "  读取来自客户端编号：" + clientId + " 的信息");
                    // 客户端显现我们要精准定位
                    clientIDMsg.put(key, clientId);
                    client.register(selector, SelectionKey.OP_WRITE);
                }
            } else if (key.isValid() && key.isWritable()) {
                // 写信息到客户端
                // 服务端确认没有收到你的请求信息 msg
                if (!sessionMsg.containsKey(key)) {
                    return;
                }
                client = (SocketChannel) key.channel();
                sendBuffer.clear();
                sendBuffer.put((sessionMsg.get(key) + "您好，Jzh已经处理完成你的请求！").getBytes());
                // 切换读写模式
                sendBuffer.flip();
                client.write(sendBuffer);
                System.out.println("当前处理线程ID：" + Thread.currentThread().getId() + "  客户端编号：" + clientId + "写出的信息为："
                        + sessionMsg.get(key) + "，您好，Jzh已经处理完成你的请求！");
                // 再注册，实际上就是驱动下一个业务的发生
                client.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            // 防止客户端非法下线
            key.cancel();
            try {
                client.socket().close();
                client.close();
                System.out.println("【系统消息提示】 北京时间：" + simpleDateFormat.format(new Date()) + "  客户端编号为："
                        + clientIDMsg.get(key) + " 已经下线！");
                clientIDMsg.remove(key);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NIOServer(9090).listener();
    }
}
