package person.jzh.hello.aop.framework;

import person.jzh.hello.aop.demo.service.impl.MemberService;
import person.jzh.hello.aop.framework.support.JAdvisedSupport;
import person.jzh.hello.aop.framework.config.JAopConfig;
import person.jzh.hello.aop.framework.support.JDKDynamicAopProxy;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author jzh
 * @version 1.0.0
 * @title JApplicationContext
 * @date 2019/12/21 14:49
 * @description：
 */
public class JApplicationContext {
    private Map<String, Object> ioc = new HashMap<>(16);
    private JDKDynamicAopProxy aopProxy;
    private Properties contextConfig = new Properties();

    public JApplicationContext() {
        JAdvisedSupport aopConfig = initAopConfig();
        aopConfig.setTargetClass(MemberService.class);
        aopProxy = new JDKDynamicAopProxy(aopConfig);
        aopConfig.setTarget(new MemberService());
        ioc.put("memberService", aopProxy.getProxy(aopConfig.getClassLoader()));
    }

    public Object getBean(String name) {
        return ioc.get(name);
    }

    private JAdvisedSupport initAopConfig() {
        JAopConfig config = new JAopConfig();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            contextConfig.load(is);
            config.setPointCut(contextConfig.getProperty("pointCut"));
            config.setAspectClass(contextConfig.getProperty("aspectClass"));
            config.setAspectBefore(contextConfig.getProperty("aspectBefore"));
            config.setAspectAfter(contextConfig.getProperty("aspectAfter"));
            config.setAspectAfterThrow(contextConfig.getProperty("aspectAfterThrow"));
            config.setAspectAfterThrowingName(contextConfig.getProperty("aspectAfterThrowingName"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new JAdvisedSupport(config);
    }
}
