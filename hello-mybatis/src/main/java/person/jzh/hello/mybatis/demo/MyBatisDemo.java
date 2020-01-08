package person.jzh.hello.mybatis.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import person.jzh.hello.mybatis.entity.Test;
import person.jzh.hello.mybatis.mapper.Tesdfsst2Mapper;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author jzh
 * @version 1.0.0
 * @title MyBatisDemo
 * @date 2020/1/7 15:32
 * @description：
 */
public class MyBatisDemo {

    public static void main(String[] args) throws FileNotFoundException {
        SqlSession sqlSession = getSqlSession();
        try {
            System.out.println(get(sqlSession, 1));
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public static SqlSession getSqlSession() throws FileNotFoundException {
        InputStream configFile = MyBatisDemo.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        return sqlSessionFactory.openSession(false);
    }

    public static Test get(SqlSession sqlSession, int id) {
        Tesdfsst2Mapper testMapper = sqlSession.getMapper(Tesdfsst2Mapper.class);
        return testMapper.selectByPrimaryKey(id);
    }

    public static int insert(SqlSession sqlSession, Test test){
        Tesdfsst2Mapper testMapper = sqlSession.getMapper(Tesdfsst2Mapper.class);
        return testMapper.insert(test);
    }
}
