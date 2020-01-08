package person.jzh.hello.mybatis.demo.v1;

/**
 * @author jzh
 * @version 1.0.0
 * @title JSqlSession
 * @date 2019/12/30 10:59
 * @description：
 */
public class JSqlSession {

    private JConfiguration configuration;

    private JExecutor executor;

    public JSqlSession(JConfiguration configuration, JExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * getMapper
     * @param clazz
     */
    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
    }

    public <T> T selectOne(String statement, String params) {
        return executor.query(statement, params);
    }
}
