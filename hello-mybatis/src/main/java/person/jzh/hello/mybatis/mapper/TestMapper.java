package person.jzh.hello.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import person.jzh.hello.mybatis.entity.Test;

/**
 * @author jzh
 * @version 1.0.0
 * @title TestMapper
 * @date 2020/1/7 15:38
 * @description：
 */
public interface TestMapper {
    Test selectByPrimaryKey(@Param("id") int id);

    int insert(Test test);
}
