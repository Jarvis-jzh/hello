package person.jzh.hello.aop.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import person.jzh.hello.aop.demo.model.Member;
import person.jzh.hello.aop.demo.service.IMemberService;

/**
 * @author jzh
 * @version 1.0.0
 * @title IMemberServiceImpl
 * @date 2019/12/21 8:45
 * @description：
 */
@Slf4j
public class MemberService implements IMemberService {

    public Member get(String id) {
        log.info("getMemberById method ...");
        return new Member();
    }

    public Member get(){
        log.info("getMember method ...");
        return new Member();
    }

    public void save(Member member) {
        log.info("save member method ...");
    }

    public Boolean delete(String id) throws Exception {
        log.info("delete method ...");
        throw new Exception("Spring support ThrowAdvice演示");
    }
}
