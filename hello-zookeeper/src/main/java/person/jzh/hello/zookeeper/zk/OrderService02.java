package person.jzh.hello.zookeeper.zk;

/**
 * @author jzh
 * @version 1.0.0
 * @title OrderService01
 * @date 2020/1/9 17:35
 * @description： 订单服务，模拟服务注册
 */
public class OrderService02 {
    public static void main(String[] args) throws Exception {
        ServiceRegistry serviceRegistry  = new ServiceRegistryImpl();
        serviceRegistry.register("order-service", "192.168.13.2:8080");
        // 阻塞线程
        System.in.read();
    }
}
