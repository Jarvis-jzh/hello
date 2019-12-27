package person.jzh.hello.spring.cloud.alibaba.nacos.consumer.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import person.jzh.hello.spring.cloud.alibaba.nacos.consumer.feign.service.fallback.NacosProviderFallback;

/**
 * @author jzh
 * @version 1.0.0
 * @title NacosProviderService
 * @date 2019/11/23 16:48
 * @description：
 */
@FeignClient(value = "nacos-provider", fallback = NacosProviderFallback.class)
public interface NacosProviderService {

    @GetMapping(value = "/echo/{message}")
    String echo(@PathVariable(value = "message")String message);
}
