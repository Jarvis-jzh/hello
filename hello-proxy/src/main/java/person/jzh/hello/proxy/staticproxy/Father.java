package person.jzh.hello.proxy.staticproxy;

import person.jzh.hello.proxy.Person;

/**
 * @author jzh
 * @version 1.0.0
 * @title Father
 * @date 2019/12/21 16:07
 * @description：
 */
public class Father implements Person {

    private Son person;

    public Father(Son person) {
        this.person = person;
    }

    @Override
    public void findLove() {
        System.out.println("父亲物色对象");
        this.person.findLove();
        System.out.println("双方父母同意，确立关系");
    }
}
