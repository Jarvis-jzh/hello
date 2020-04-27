package person.jzh.hello.security.pojo;

import lombok.Data;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:56
 * @description 
 */
@Data
public class Role {
    private Integer id;

    /**
    * 权限名
    */
    private String name;
}