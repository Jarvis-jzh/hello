package person.jzh.hello.security.pojo;

import lombok.Data;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:56
 * @description 
 */
@Data
public class UserRole {
    private Integer id;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 权限id
    */
    private Integer roleId;
}