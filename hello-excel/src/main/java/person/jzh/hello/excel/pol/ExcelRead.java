package person.jzh.hello.excel.pol;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/17 17:46
 * @description
 */
public class ExcelRead {
    // 保存路径
    static String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";

    public static void main(String[] args) throws Exception {
        //readExcel03(new HSSFWorkbook(new FileInputStream(path + "03.xls")));
        //readExcel03(new XSSFWorkbook(new FileInputStream(path + "07.xlsx")));
        //cellType();
        testFormula();
    }

    public static void readExcel03(Workbook workbook) throws IOException {
        // 获取第一个表
        Sheet sheet = workbook.getSheetAt(0);

        // 获取行
        Row row = sheet.getRow(0);
        // 获取列
        Cell cell = row.getCell(0);

        System.out.println(cell.getStringCellValue());
    }

    public static void cellType() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path + "明细表.xls");
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // 获取标题内容
        Row rowTitle = sheet.getRow(0);
        if (rowTitle != null) {
            int cellCount = rowTitle.getPhysicalNumberOfCells();
            for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                Cell cell = rowTitle.getCell(cellNum);
                if (cell != null) {
                    // int cellType = cell.getCellType();
                    String cellValue = cell.getStringCellValue();
                    System.out.print(cellValue + " | ");
                }
            }
            System.out.println();
        }

        // 获取表中的内容
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null) {
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    // 匹配数列的数据类型
                    if (cell != null) {
                        int cellType = cell.getCellType();
                        String cellValue = "";

                        switch (cellType) {
                            case HSSFCell.CELL_TYPE_STRING:
                                // 字符串
                                System.out.print("[String]");
                                cellValue = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                // 布尔
                                System.out.print("[Boolean]");
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                // 空
                                System.out.print("[Blank]");
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                System.out.print("[Number]");
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    System.out.println("日期");
                                    Date date = cell.getDateCellValue();
                                    cellValue = new DateTime(date).toString("yyyy-MM-dd");
                                } else {
                                    // 不是日期格式，防止数字过长
                                    System.out.println("[转换为字符串输出]");
                                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                    cellValue = cell.getStringCellValue();
                                }
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                System.out.println("[数据类型错误]");
                                break;
                        }
                        System.out.println(cellValue);
                    }
                }
            }
        }
        fileInputStream.close();
    }

    public static void testFormula() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(path + "公式.xls");
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(4);
        Cell cell = row.getCell(0);

        // 拿到计算公式
        FormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);

        // 输出单元格的内容
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_FORMULA:
                String formula = cell.getCellFormula();
                System.out.println(formula);

                // 计算
                CellValue evaluate = formulaEvaluator.evaluate(cell);
                String cellValue = evaluate.formatAsString();
                System.out.println(cellValue);
                break;
        }
    }
}
