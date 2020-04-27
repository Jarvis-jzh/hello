package person.jzh.hello.security.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import person.jzh.hello.security.pojo.UserRole;
import person.jzh.hello.security.mapper.UserRoleMapper;
import person.jzh.hello.security.service.UserRoleService;
/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:56
 * @description 
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserRole record) {
        return userRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(UserRole record) {
        return userRoleMapper.insertSelective(record);
    }

    @Override
    public UserRole selectByPrimaryKey(Integer id) {
        return userRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserRole record) {
        return userRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserRole record) {
        return userRoleMapper.updateByPrimaryKey(record);
    }

}
