package person.jzh.hello.proxy.staticproxy;

import person.jzh.hello.proxy.Person;

/**
 * @author jzh
 * @version 1.0.0
 * @title Son
 * @date 2019/12/21 16:07
 * @description：
 */
public class Son implements Person {
    @Override
    public void findLove() {
        System.out.println("儿子要求：肤白貌美大长腿");
    }
}
