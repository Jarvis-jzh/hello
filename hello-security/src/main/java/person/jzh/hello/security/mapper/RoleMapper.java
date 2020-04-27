package person.jzh.hello.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import person.jzh.hello.security.pojo.Role;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:56
 * @description 
 */
@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}