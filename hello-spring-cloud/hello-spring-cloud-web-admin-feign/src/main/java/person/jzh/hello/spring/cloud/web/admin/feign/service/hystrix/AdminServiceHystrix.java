package person.jzh.hello.spring.cloud.web.admin.feign.service.hystrix;

import org.springframework.stereotype.Component;
import person.jzh.hello.spring.cloud.web.admin.feign.service.AdminService;

/**
 * @author jzh
 * @date 2019/9/17 9:51
 * @description
 */
@Component
public class AdminServiceHystrix implements AdminService {

    @Override
    public String sayHi(String message) {
        return String.format("Hi your message is : %s but request bad", message);
    }
}
