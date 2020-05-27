package person.jzh.hello.excel.pol;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/17 16:53
 * @description 使用 pol 生成 excel
 */
public class ExcelWrite {
    // 保存路径
    static String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";

    public static void main(String[] args) throws IOException, URISyntaxException {
        // 1、创建一个工作簿 03版本
        createExcel(new HSSFWorkbook(), "03.xls");

        // 07版本
        createExcel(new XSSFWorkbook(), "07.xlsx");

        // 03 大数据文件
        createExcelBigData(new HSSFWorkbook(), "textWrite03BigData.xls");

        // 07 大数据文件
        createExcelBigData(new XSSFWorkbook(), "textWrite07BigData.xlsx");

        // 使用 SXSS 快速生成大数据文件
        createExcelSXSS();
    }

    /**
     * 创建小数据 excel 文件
     *
     * @param workbook
     * @param filename
     * @throws IOException
     */
    public static void createExcel(Workbook workbook, String filename) throws IOException {
        // 2、创建一个工作表
        Sheet sheet = workbook.createSheet("观众统计表");
        // 3、创建一个行
        Row row1 = sheet.createRow(0);
        // 4、创建一个单元格
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今日新增观众");
        Cell cell2 = row1.createCell(1);
        cell2.setCellValue("666");

        // 第二行 （2，1）
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");

        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        // 生成一张表
        // System.out.println(System.getProperty("user.dir"));
        FileOutputStream fileOutputStream = new FileOutputStream(path + filename);
        workbook.write(fileOutputStream);

        fileOutputStream.close();

        System.out.println(filename + "生成完毕");
    }

    /**
     * 创建大数据量的 excel
     */
    public static void createExcelBigData(Workbook workbook, String filename) throws IOException {
        long start = System.currentTimeMillis();

        // 创建一个表
        Sheet sheet = workbook.createSheet();
        // 写入数据
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }

        System.out.println("over");

        FileOutputStream fileOutputStream = new FileOutputStream(path + filename);
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        long end = System.currentTimeMillis();
        System.out.println((double) (end - start) / 1000);
    }

    public static void createExcelSXSS() throws IOException {
        long start = System.currentTimeMillis();

        // 创建一个簿
        Workbook workbook = new SXSSFWorkbook();
        // 创建一个表
        Sheet sheet = workbook.createSheet();
        // 写入数据
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }

        System.out.println("over");

        FileOutputStream fileOutputStream = new FileOutputStream(path + "textWrite07S.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        // 清除临时文件
        ((SXSSFWorkbook) workbook).dispose();

        long end = System.currentTimeMillis();
        System.out.println((double) (end - start) / 1000);
    }
}
