package person.jzh.hello.dubbo.service.user.provider.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import person.jzh.hello.dubbo.service.user.api.UserService;

/**
 * @author jzh
 * @version 1.0.0
 * @title UserServiceImpl
 * @date 2019/12/25 17:45
 * @description：
 */
@Service(version = "${user.service.version}")
public class UserServiceImpl implements UserService {

    @Value("${dubbo.protocol.port}")
    private String port;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    @Override
    public String sayHi() {
//        return "Hello Dubbo, i am from port: " + port;
        throw new RuntimeException("Exception to show hystrix enabled.");
    }
}
