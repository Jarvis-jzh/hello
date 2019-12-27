package person.jzh.spring.demo.aspect;

/**
 * @author jzh
 * @version 1.0.0
 * @title LogAspect
 * @date 2019/12/10 10:41
 * @description：
 */
public class LogAspect {

    public void before(){
        // 往对象里记录调用的开始时间
        System.out.println();
    }

    public void after(){
        // 系统当前时间-之前记录的开始时间=方法的调用所消耗的时间
    }

    public void afterThrowing(){
        // 异常监测，可以拿到异常的信息
    }
}
