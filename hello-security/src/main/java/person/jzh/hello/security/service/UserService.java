package person.jzh.hello.security.service;

import person.jzh.hello.security.pojo.User;
    /**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:55
 * @description 
 */
public interface UserService{


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
