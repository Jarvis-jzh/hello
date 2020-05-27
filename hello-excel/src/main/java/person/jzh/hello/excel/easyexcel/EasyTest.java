package person.jzh.hello.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;
import person.jzh.hello.excel.easyexcel.listener.DemoDataListener;
import person.jzh.hello.excel.easyexcel.pojo.DemoData;
import person.jzh.hello.excel.pol.ExcelWrite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/18 11:13
 * @description
 */
public class EasyTest {
    // 保存路径
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i<10 ; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    @Test
    public void simpleWrite1() {
        // 写法1
        String fileName = path + "easytest.xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }

    @Test
    public void simpleWrite2() {
        // 写法2
        String fileName = path + "easytest.xlsx";
        // 这里需要指定写用哪个 class 去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();

        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data(), writeSheet);
        // 千万别忘记 finish 会帮忙关闭流
        excelWriter.finish();
    }

    @Test
    public void simpleRead() {
        String fileName = path + "easytest.xlsx";
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }
}
