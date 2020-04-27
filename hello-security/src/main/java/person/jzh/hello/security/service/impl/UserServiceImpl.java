package person.jzh.hello.security.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import person.jzh.hello.security.pojo.User;
import person.jzh.hello.security.mapper.UserMapper;
import person.jzh.hello.security.service.UserService;
/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:55
 * @description 
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

}
