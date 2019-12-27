package person.jzh.hello.spring.cloud.alibaba.nacos.consumer.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jzh
 * @version 1.0.0
 * @title NacosConsumerFeignApplication
 * @date 2019/11/23 16:47
 * @description：
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumerFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerFeignApplication.class, args);
    }
}
