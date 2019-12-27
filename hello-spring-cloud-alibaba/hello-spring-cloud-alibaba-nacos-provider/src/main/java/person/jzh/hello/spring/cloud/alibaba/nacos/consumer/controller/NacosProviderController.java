package person.jzh.hello.spring.cloud.alibaba.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jzh
 * @version 1.0.0
 * @title NacosProviderController
 * @date 2019/11/23 11:23
 * @description：
 */
@RefreshScope
@RestController
public class NacosProviderController {

    @Value("${server.port}")
    private String port;

    @Value("${user.name}")
    private String name;

//    @Autowired
//    private ConfigurableApplicationContext applicationContext;

    @GetMapping(value = "/echo/{message}")
    public String echo(@PathVariable(value = "message") String message) {
        return "Hello Nacos " + message + " i am from " + port;
    }

//    @GetMapping(value = "/hi")
//    public String hi(){
//        String name = applicationContext.getEnvironment().getProperty("user.name");
//        return name;
//    }

    @GetMapping(value = "/hi2")
    public String hi2(){
        return name;
    }
}
