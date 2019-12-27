package person.jzh.rpc.api;

import person.jzh.rpc.annotation.ServiceMapped;
import person.jzh.rpc.dto.UserDTO;

/**
 * @author jzh
 * @version 1.0.0
 * @title UserService
 * @date 2019/12/18 20:35
 * @description：
 */
@ServiceMapped("person.jzh.rpc.api.impl.UserServiceImpl")
public interface UserService {
    UserDTO addUser(UserDTO userDTO);
}
