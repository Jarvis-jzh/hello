package person.jzh.hello.zookeeper.consumer;

import java.util.List;
import java.util.Random;

/**
 * @author jzh
 * @version 1.0.0
 * @title RandomStrategy
 * @date 2020/1/9 17:59
 * @description： 随机算法
 */
public class RandomStrategy extends AbstractLoadBalance {
    @Override
    protected String doSelect(List<String> invokers) {
        int len = invokers.size();
        Random random = new Random();
        return invokers.get(random.nextInt(len));
    }
}
