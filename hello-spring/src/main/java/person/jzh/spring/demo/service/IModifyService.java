package person.jzh.spring.demo.service;

/**
 * @author jzh
 * @version 1.0.0
 * @title IModifyService
 * @date 2019/12/3 8:48
 * @description：
 */
public interface IModifyService {

    String add(String name,String addr) throws Exception;

    String edit(Integer id,String name);

    String remove(Integer id);
}
