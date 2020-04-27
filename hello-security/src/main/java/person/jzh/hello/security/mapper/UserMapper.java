package person.jzh.hello.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import person.jzh.hello.security.pojo.User;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:55
 * @description
 */
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserAndRoleByUsername(@Param("username") String username);
}