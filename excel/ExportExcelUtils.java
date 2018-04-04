package com.netease.music.musician.excel;


import com.netease.music.musician.exception.BaseErrorEnum;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public final class ExportExcelUtils {

    /***
     * 构造方法
     */
    private ExportExcelUtils() {

    }

    /***
     * 工作簿
     */
    private static HSSFWorkbook workbook;

    /***
     * sheet
     */
    private static HSSFSheet sheet;
    /***
     * 标题行开始位置
     */
    private static final int TITLE_START_POSITION = 0;

    /***
     * 时间行开始位置
     */
    private static final int DATEHEAD_START_POSITION = 1;

    /***
     * 表头行开始位置
     */
    private static final int HEAD_START_POSITION = 2;

    /***
     * 文本行开始位置
     */
    private static final int CONTENT_START_POSITION = 3;


    /**
     * 生成excel报表
     *
     * @param properties 传入参数
     * @return
     */
    public static File excelExport(ExcelProperties properties) throws ExcelException {

        Map<String, String> titleMap = properties.getTitleMap();
        String date = properties.getDate();
        String sheetName = properties.getSheetName();
        List<T> dataList = properties.getDataList();
        String fileUrl = properties.getFileUrl();
        // 初始化workbook
        initHSSFWorkbook(sheetName);
        // 标题行
        createTitleRow(titleMap, sheetName);
        // 时间行
        createDateHeadRow(titleMap, date);
        // 表头行
        createHeadRow(titleMap);
        // 文本行
        createContentRow(dataList, titleMap);
        // 写入处理结果
        try {
            OutputStream out = new FileOutputStream(fileUrl);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            throw new ExcelException(BaseErrorEnum.EXCEL_PROCESS_ERROR.getErrorCode(), BaseErrorEnum.EXCEL_PROCESS_ERROR.getDesc());
        }
        return new File(fileUrl);
    }

    /***
     * @param sheetName sheetName
     */
    private static void initHSSFWorkbook(String sheetName) {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    /**
     * 生成标题（第零行创建）
     *
     * @param titleMap  对象属性名称->表头显示名称
     * @param sheetName sheet名称
     */
    private static void createTitleRow(Map<String, String> titleMap, String sheetName) {
        CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, titleMap.size() - 1);
        sheet.addMergedRegion(titleRange);
        HSSFRow titleRow = sheet.createRow(TITLE_START_POSITION);
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(sheetName);
    }

    /**
     * 创建时间行（第一行创建）
     *
     * @param titleMap 对象属性名称->表头显示名称
     */
    private static void createDateHeadRow(Map<String, String> titleMap, String date) {
        CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, titleMap.size() - 1);
        sheet.addMergedRegion(dateRange);
        HSSFRow dateRow = sheet.createRow(DATEHEAD_START_POSITION);
        HSSFCell dateCell = dateRow.createCell(0);
        dateCell.setCellValue("时间:" + date);
    }

    /**
     * 创建表头行（第二行创建）
     *
     * @param titleMap 对象属性名称->表头显示名称
     */
    private static void createHeadRow(Map<String, String> titleMap) {
        // 第1行创建
        HSSFRow headRow = sheet.createRow(HEAD_START_POSITION);
        int i = 0;
        for (String entry : titleMap.keySet()) {
            HSSFCell headCell = headRow.createCell(i);
            headCell.setCellValue(titleMap.get(entry));
            i++;
        }
    }

    /**
     * @param dataList 对象数据集合
     * @param titleMap 表头信息
     */
    private static void createContentRow(List<?> dataList, Map<String, String> titleMap) throws ExcelException {
        try {
            int i = 0;
            for (Object obj : dataList) {
                HSSFRow textRow = sheet.createRow(CONTENT_START_POSITION + i);
                int j = 0;
                for (String entry : titleMap.keySet()) {
                    String method = "get" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
                    Method m = obj.getClass().getMethod(method, null);
                    String value = "";
                    try {
                        //允许目标对象包含空属性
                        Object field = m.invoke(obj, null);
                        if (field != null) {
                            value = field.toString();
                        }
                    } catch (Exception e) {
                        throw new ExcelException(BaseErrorEnum.EXCEL_PROCESS_ERROR.getErrorCode(), BaseErrorEnum.EXCEL_PROCESS_ERROR.getDesc());
                    }
                    HSSFCell textcell = textRow.createCell(j);
                    textcell.setCellValue(value);
                    j++;
                }
                i++;
            }

        } catch (Exception e) {
            throw new ExcelException(BaseErrorEnum.EXCEL_PROCESS_ERROR.getErrorCode(), BaseErrorEnum.EXCEL_PROCESS_ERROR.getDesc());
        }
    }

}