package person.jzh.hello.zookeeper.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author jzh
 * @version 1.0.0
 * @title ZkDemo
 * @date 2020/1/9 16:53
 * @description：
 */
public class ZkDemo {
    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    public void add() throws Exception {
        curatorFramework.create()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/test/demo", "hello zookeeper".getBytes());
//        curatorFramework.getData().forPath();
//        curatorFramework.setData().forPath();
//        curatorFramework.delete().forPath();
    }

    public static void main(String[] args) throws Exception {
        new ZkDemo().add();
    }
}
