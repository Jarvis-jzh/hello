package person.jzh.hello.spring.cloud.alibaba.nacos.consumer.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import person.jzh.hello.spring.cloud.alibaba.nacos.consumer.feign.service.NacosProviderService;

/**
 * @author jzh
 * @version 1.0.0
 * @title NacosProviderController
 * @date 2019/11/23 16:53
 * @description：
 */
@RestController
public class NacosProviderController {

    @Autowired
    private NacosProviderService nacosProviderService;

    @GetMapping(value = "/echo")
    public String echo(){
        return nacosProviderService.echo("Hello Feign");
    }
}
