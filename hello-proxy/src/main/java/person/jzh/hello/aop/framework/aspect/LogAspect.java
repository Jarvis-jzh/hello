package person.jzh.hello.aop.framework.aspect;


import lombok.extern.slf4j.Slf4j;

/**
 * @author jzh
 * @version 1.0.0
 * @title LogAspect
 * @date 2019/12/20 19:55
 * @description：
 */
@Slf4j
public class LogAspect {
    /**
     * 方法之前执行
     */
    public void before() {
        log.info("Invoker Before Method!!!");
    }

    /**
     * 方法之后执行
     */
    public void after() {
        log.info("Invoker After Method!!!");
    }

    /**
     * 异常后执行
     */
    public void afterThrowing() {
        log.info("出现异常！！！");
    }
}
