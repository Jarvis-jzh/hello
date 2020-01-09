package person.jzh.hello.zookeeper.zk;

/**
 * @author jzh
 * @version 1.0.0
 * @title ServiceRegistry
 * @date 2020/1/9 17:13
 * @description： 服务注册
 */
public interface ServiceRegistry {
    /**
     * 服务注册
     * @param serviceName       服务名
     * @param serviceAddress    服务地址
     */
    void register(String serviceName, String serviceAddress) throws Exception;
}
