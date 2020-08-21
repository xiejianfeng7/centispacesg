package cn.nci.util;

import cn.nci.domain.QueryTelemetryParameters;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @program: centispacesg
 * @description: 遥测信息导出到Excel表格中
 * @author: xiejianfeng
 * @create: 2020-08-21 15:38
 */
public class ExcelUtil {
    /**
     * 2、将数据库中的数据写入Excel表格
     */
    public static void write(List<QueryTelemetryParameters> productList, String fileName) throws IOException {
        // 1、创建一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 2、创建工作表
        XSSFSheet sheet = workbook.createSheet("遥测参数");

        // 获取单元格的样式对象
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置单元格样式
        cellStyle.setFillForegroundColor(IndexedColors.PINK.getIndex());
        // 设置
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        // 改变字体
        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setColor(IndexedColors.BLUE.getIndex());
        // 将字体样式应用到单元格样式中
        cellStyle.setFont(font);

        // 创建首行，首先创建第一行的表头信息
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("CodeID");
        row.createCell(1).setCellValue("SignalGndTime");
        row.createCell(2).setCellValue("SignalSatTime");
        row.createCell(3).setCellValue("OriginalValue");
        row.createCell(4).setCellValue("EngineerValue");
        row.createCell(5).setCellValue("StateValue");

        // 修改表头样式时不能再使用链式编程需要先创建对象，然后再做设置
//        XSSFCell cell = row.createCell(0);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("商品编号");

        // 从集合中拿出数据
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList);
            XSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(productList.get(i).getCodeID());
            row1.createCell(1).setCellValue(productList.get(i).getSignalGndTime().toString());
            row1.createCell(2).setCellValue(productList.get(i).getSignalSatTime().toString());
            row1.createCell(3).setCellValue(productList.get(i).getOriginalValue());
            row1.createCell(4).setCellValue(productList.get(i).getEngineerValue());
            row1.createCell(5).setCellValue(productList.get(i).getStateValue());

        }
        FileOutputStream fos = new FileOutputStream(new File(fileName));
        workbook.write(fos);

        fos.flush();
        fos.close();
        workbook.close();
    }
}
