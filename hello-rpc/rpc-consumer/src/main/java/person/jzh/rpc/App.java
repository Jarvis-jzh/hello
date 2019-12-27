package person.jzh.rpc;

import person.jzh.rpc.api.UserService;
import person.jzh.rpc.dto.UserDTO;
import person.jzh.rpc.factory.ProxyFactory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        UserService userService = ProxyFactory.getService(UserService.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("张三");
        userDTO.setAge(18);
        userDTO = userService.addUser(userDTO);
        System.out.println(userDTO);
        // 在客户端调用的过程中，要尽量保证语义表达的简洁

    }
}
