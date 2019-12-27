package person.jzh.hello.aop.demo.service;


import person.jzh.hello.aop.demo.model.Member;

/**
 * @author jzh
 * @version 1.0.0
 * @title IMemberService
 * @date 2019/12/21 8:44
 * @description：
 */
public interface IMemberService {

    Member get(String id);

    Member get();

    void save(Member member);

    Boolean delete(String id) throws Exception;
}
