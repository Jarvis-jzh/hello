package person.jzh.hello.spring.cloud.service.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jzh
 * @date 2019/9/16 14:37
 * @description
 */
@RestController
public class AdminController {
    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String sayHi(String message){
        return String.format("hi your message is : %s port : %s", message, port);
    }
}
