package person.jzh.hello.mybatis.demo.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jzh
 * @version 1.0.0
 * @title JMapperProxy
 * @date 2020/1/8 16:11
 * @description：
 */
public class JMapperProxy implements InvocationHandler {

    private JSqlSession sqlSession;

    public JMapperProxy(JSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().getName()
                .equals(JConfiguration.TestMapperXML.namespace)) {
            return sqlSession.selectOne(
                    JConfiguration.TestMapperXML.sqlMappings.get(method.getName()),
                    String.valueOf(args[0]));
        }
        return method.invoke(this, args);
    }
}
