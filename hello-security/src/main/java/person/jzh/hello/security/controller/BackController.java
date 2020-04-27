package person.jzh.hello.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 23:08
 * @description
 */
@Controller
public class BackController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/superadmin")
    public String superAdmin() {
        return "superAdmin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
