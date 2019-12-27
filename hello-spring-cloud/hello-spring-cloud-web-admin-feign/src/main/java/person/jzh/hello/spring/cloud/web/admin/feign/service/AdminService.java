package person.jzh.hello.spring.cloud.web.admin.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import person.jzh.hello.spring.cloud.web.admin.feign.service.hystrix.AdminServiceHystrix;

/**
 * @author jzh
 * @date 2019/9/16 17:26
 * @description
 */
@FeignClient(value = "hello-spring-cloud-service-admin", fallback = AdminServiceHystrix.class)
public interface AdminService {

    /**
     * 一定要加上@RequestParam注解 不然会报错
     * @param message
     * @return
     */
    @RequestMapping(value = "hi", method = RequestMethod.GET)
    String sayHi(@RequestParam("message") String message);
}
