package person.jzh.hello.proxy.dynamicproxy.Jdkproxy;

import person.jzh.hello.proxy.Person;

/**
 * @author jzh
 * @version 1.0.0
 * @title Girl
 * @date 2019/12/21 8:53
 * @description：
 */
public class Girl implements Person {
    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("身高180cm");
    }
}
