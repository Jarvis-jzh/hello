package person.jzh.rpc.api.impl;

import person.jzh.rpc.api.UserService;
import person.jzh.rpc.dto.UserDTO;

import java.util.Random;

/**
 * @author jzh
 * @version 1.0.0
 * @title UserServiceImpl
 * @date 2019/12/20 15:55
 * @description：
 */
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO addUser(UserDTO userDTO) {
        System.out.println("userDTO: " + userDTO.toString());
        userDTO.setUserId(new Random().nextInt() + "");
        return userDTO;
    }
}
