package person.jzh.hello.spring.cloud.alibaba.nacos.consumer.feign.service.fallback;

import org.springframework.stereotype.Component;
import person.jzh.hello.spring.cloud.alibaba.nacos.consumer.feign.service.NacosProviderService;

/**
 * @author jzh
 * @version 1.0.0
 * @title NacosProviderFallback
 * @date 2019/11/23 17:21
 * @description：
 */
@Component
public class NacosProviderFallback implements NacosProviderService {
    @Override
    public String echo(String message) {
        return "请求失败，请检查你的网络";
    }
}
