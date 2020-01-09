package person.jzh.hello.zookeeper.consumer;

import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title AbstractLoadBalance
 * @date 2020/1/9 17:57
 * @description：
 */
public abstract class AbstractLoadBalance implements LoadBalanceStrategy {

    @Override
    public String selectHost(List<String> repos) {
        if (repos == null || repos.size() == 0){
            return null;
        }
        if (repos.size() == 1){
            return repos.get(0);
        }
        return doSelect(repos);
    }
    // 实现负载均衡算法
    protected abstract String doSelect(List<String> invokers);
}
