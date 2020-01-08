package person.jzh.hello.mybatis.demo.v1;

import person.jzh.hello.mybatis.entity.Test;
import person.jzh.hello.mybatis.mapper.Tesdfsst2Mapper;

/**
 * @author jzh
 * @version 1.0.0
 * @title MyBatisV1Demo
 * @date 2020/1/8 17:14
 * @description：
 */
public class MyBatisV1Demo {
    public static void main(String[] args) {
        JConfiguration configuration = new JConfiguration();
        JSqlSession sqlSession = new JSqlSession(configuration, new JExecutor());
        Tesdfsst2Mapper mapper = sqlSession.getMapper(Tesdfsst2Mapper.class);
        Test test = mapper.selectByPrimaryKey(1);
        System.out.println(test);
    }
}
