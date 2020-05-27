package person.jzh.hello.excel.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import person.jzh.hello.excel.easyexcel.dao.DemoDAO;
import person.jzh.hello.excel.easyexcel.pojo.DemoData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/18 11:58
 * @description 有个很重要的点 DemoDataListener 不能被 Spring 管理，要每次读取 excel 都要 new，然后里面用到的 Spring 可以构造方法传进去
 */
@Slf4j
public class DemoDataListener extends AnalysisEventListener<DemoData> {
    /**
     * 每隔 5 条存储数据库，实际使用中可以 3000 条，然后清理 list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    List<DemoData> list = new ArrayList<>();

    /**
     * 假设这个是一个 DAO，当然有业务逻辑这个也可以是一个 service，当然如果不用存储这个对象没用。
     */
    private DemoDAO demoDAO;

    public DemoDataListener() {
        // 这里是一个 demo，所以随便 new 一个，实际使用如果到了 spring，请使用下面的有参构造函数
        demoDAO = new DemoDAO();
    }

    /**
     * 如果使用了 Spring，请使用这个构造方法，每次创建 listener 的时候需要把 Spring 管理的类传进来
     * @param demoDAO
     */
    public DemoDataListener(DemoDAO demoDAO) {
        this.demoDAO = demoDAO;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param analysisContext       分析上下文
     */
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JSON.toJSONString(data));
        list.add(data);
        // 达到 BATCH_COUNT 了，需要去存储一次数据库，防止数据几万条数据在内存，容易 OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了，都会来调用
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    public void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        demoDAO.save(list);
        log.info("存储数据库成功！");
    }
}
