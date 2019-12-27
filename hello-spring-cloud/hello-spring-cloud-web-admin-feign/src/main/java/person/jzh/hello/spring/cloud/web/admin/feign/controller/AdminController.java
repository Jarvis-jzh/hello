package person.jzh.hello.spring.cloud.web.admin.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import person.jzh.hello.spring.cloud.web.admin.feign.service.AdminService;

/**
 * @author jzh
 * @date 2019/9/16 17:28
 * @description
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String sayHi(String message){
        return adminService.sayHi(message);
    }
}
