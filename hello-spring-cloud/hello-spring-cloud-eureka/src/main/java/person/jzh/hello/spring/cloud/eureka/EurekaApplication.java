package person.jzh.hello.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author jzh
 * @date 2019/9/16 14:10
 * @description 服务注册和发现（服务端）
 * 启动顺序：
 *      1、服务注册与发现
 *      2、分布式配置中心
 *      3、服务提供者
 *      4、服务消费者
 *      5、API网关
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
