package person.jzh.hello.zookeeper.consumer;

import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @title RoundbinStrategy
 * @date 2020/1/9 18:00
 * @description： 轮询算法
 */
public class RoundbinStrategy extends AbstractLoadBalance{
    @Override
    protected String doSelect(List<String> invokers) {
        // 轮询
        return null;
    }
}
