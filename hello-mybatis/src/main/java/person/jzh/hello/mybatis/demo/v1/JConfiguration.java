package person.jzh.hello.mybatis.demo.v1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jzh
 * @version 1.0.0
 * @title JConfiguration
 * @date 2019/12/30 10:59
 * @description：
 */
public class JConfiguration {

    public <T> T getMapper(Class<T> clazz, JSqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{clazz},
                new JMapperProxy(sqlSession));
    }

    static class TestMapperXML{
        public static final String namespace = "person.jzh.hello.mybatis.mapper.TestMapper";

        public static final Map<String, String> sqlMappings = new HashMap<>();

        static {
            sqlMappings.put("selectByPrimaryKey", "select * from test t where t.id = ?;");
        }
    }
}
