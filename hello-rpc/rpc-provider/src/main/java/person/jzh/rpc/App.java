package person.jzh.rpc;

import person.jzh.rpc.net.BIOServer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        BIOServer.startServer(7777);
    }
}
