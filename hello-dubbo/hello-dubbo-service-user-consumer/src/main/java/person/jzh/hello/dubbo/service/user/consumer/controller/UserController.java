package person.jzh.hello.dubbo.service.user.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import person.jzh.hello.dubbo.service.user.api.UserService;

/**
 * @author jzh
 * @version 1.0.0
 * @title UserController
 * @date 2019/12/26 8:37
 * @description：
 */
@RestController
public class UserController {
    @Reference(version = "${user.service.version}")
    private UserService userService;

//    @HystrixCommand(fallbackMethod = "hiError")
    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String sayHi() {
        return userService.sayHi();
    }

    public String hiError() {
        return "Hello Hystrix";
    }
}
