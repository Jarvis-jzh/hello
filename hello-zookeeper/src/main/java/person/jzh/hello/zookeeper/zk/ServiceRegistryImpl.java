package person.jzh.hello.zookeeper.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author jzh
 * @version 1.0.0
 * @title ServiceRegistryImpl
 * @date 2020/1/9 17:14
 * @description：
 */
public class ServiceRegistryImpl implements ServiceRegistry {

    private CuratorFramework curatorFramework;

    private static final String REGISTRY_ROOT = "/registry";

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        String servicePath = REGISTRY_ROOT + "/" + serviceName;
        try {
            // 创建一个项目节点
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath, "0".getBytes());
            }

            String addressPath = servicePath + "/" + serviceAddress;
            String rs = curatorFramework.create()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(addressPath, "0".getBytes());
            System.out.println(serviceName + "---服务注册成功：" + rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
