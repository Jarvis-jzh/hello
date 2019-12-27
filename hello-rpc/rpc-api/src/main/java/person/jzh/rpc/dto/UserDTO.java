package person.jzh.rpc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jzh
 * @version 1.0.0
 * @title UserDTO
 * @date 2019/12/18 20:33
 * @description：
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -5217069794202364802L;

    private String userId;

    private String userName;

    private int age;

}
