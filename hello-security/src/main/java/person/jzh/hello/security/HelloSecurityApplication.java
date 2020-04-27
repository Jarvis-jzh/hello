package person.jzh.hello.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:40
 * @description
 */
@SpringBootApplication
@MapperScan(basePackages = "person.jzh.hello.security.mapper")
public class HelloSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloSecurityApplication.class, args);
    }
}
