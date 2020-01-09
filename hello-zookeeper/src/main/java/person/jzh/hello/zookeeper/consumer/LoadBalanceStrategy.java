package person.jzh.hello.zookeeper.consumer;

import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title LoadBalanceStrategy
 * @date 2020/1/9 17:56
 * @description：
 */
public interface LoadBalanceStrategy {
    String selectHost(List<String> repos);
}
