package person.jzh.hello.excel.easyexcel.dao;

import person.jzh.hello.excel.easyexcel.pojo.DemoData;

import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/18 11:56
 * @description 假设这个是你的 DAO 存储，当然还要这个类让 Spring 管理，当然你不需要存储也不需要这个类
 */
public class DemoDAO {
    public void save(List<DemoData> list) {
        // 如果是 mybatis，尽量别直接调用多次 insert，自己写一个 mapper 里面新增一个方法 batchInsert，所有数据一次性插入

    }
}
