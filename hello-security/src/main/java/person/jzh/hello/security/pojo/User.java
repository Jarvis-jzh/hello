package person.jzh.hello.security.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:55
 * @description 
 */
@Data
public class User {
    private Integer id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码（加密）
    */
    private String password;

    List<Role> roles;
}