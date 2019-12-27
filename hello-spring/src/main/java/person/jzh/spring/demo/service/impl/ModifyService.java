package person.jzh.spring.demo.service.impl;


import person.jzh.spring.demo.service.IModifyService;
import person.jzh.spring.formework.annotation.JService;

/**
 * @author jzh
 * @version 1.0.0
 * @title ModifyService
 * @date 2019/12/10 10:41
 * @description：
 */
@JService
public class ModifyService implements IModifyService {

	/**
	 * 增加
	 */
	public String add(String name,String addr) throws Exception {
		throw new Exception("这是Jzh故意抛的异常！！");
		//return "modifyService add,name=" + name + ",addr=" + addr;
	}

	/**
	 * 修改
	 */
	public String edit(Integer id,String name) {
		return "modifyService edit,id=" + id + ",name=" + name;
	}

	/**
	 * 删除
	 */
	public String remove(Integer id) {
		return "modifyService id=" + id;
	}
	
}
