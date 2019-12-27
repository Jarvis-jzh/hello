package person.jzh.hello.spring.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @author jzh
 * @date 2019/9/19 9:24
 * @description 服务追踪组件 ZipKin
 */
@EnableZipkinServer
@EnableEurekaClient
@SpringBootApplication
public class ZipKinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipKinApplication.class, args);
    }
}
