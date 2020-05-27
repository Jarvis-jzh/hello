package person.jzh.hello.elasticsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/27 15:28
 * @description
 */
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}
