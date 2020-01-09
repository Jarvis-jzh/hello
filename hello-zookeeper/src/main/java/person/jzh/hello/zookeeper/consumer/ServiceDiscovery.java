package person.jzh.hello.zookeeper.consumer;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title ServiceDiscovery
 * @date 2020/1/9 17:45
 * @description：
 */
public class ServiceDiscovery {
    // 保存指定的服务端 url 地址
    List<String> serviceRepos = new ArrayList<>();

    private CuratorFramework curatorFramework;

    private static final String REGISTRY_ROOT = "/registry";

    private LoadBalanceStrategy loadBalanceStrategy;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
        loadBalanceStrategy = new RandomStrategy();
    }

    public void init(String serviceName) {
        // 获取指定项目名称下的所有服务节点
        String path = REGISTRY_ROOT + "/" + serviceName;
        try {
            serviceRepos = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 注册监听
        try {
            registerWatcher(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 针对指定的节点注册监听
     *
     * @param path
     */
    private void registerWatcher(final String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                // 触发事件
                // 再一次获取所有节点
                serviceRepos = client.getChildren().forPath(path);
                // 再调用一次，触发监听
                // client.checkExists().usingWatcher();
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }

    public String getEndPoint() {
        return loadBalanceStrategy.selectHost(serviceRepos);
    }

    public static void main(String[] args) throws InterruptedException {
        ServiceDiscovery sd = new ServiceDiscovery();
        sd.init("order-service");
        for (int i = 0; i < 30; i++) {
            System.out.println("当前调用的服务地址：" + sd.getEndPoint());
            Thread.sleep(4000);
        }
    }
}
