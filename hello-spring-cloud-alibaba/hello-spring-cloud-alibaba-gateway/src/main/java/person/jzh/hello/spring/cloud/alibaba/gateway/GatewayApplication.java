package person.jzh.hello.spring.cloud.alibaba.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jzh
 * @version 1.0.0
 * @title GatewayApplication
 * @date 2019/11/24 14:12
 * @description：
 * Gateway 基于 spring 5.x，它的服务用的是webflux，底层用的是 Netty，实现的异步非阻塞
 * 它已经有服务了，既然用了webflux，那就不能用starter-web了
 * 如果引入web（tomcat），这两个服务会有冲突,
 * 但 Gateway 需要使用过渡器，过滤器需要用到 servlet，所以需要依赖 servlet-api
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
