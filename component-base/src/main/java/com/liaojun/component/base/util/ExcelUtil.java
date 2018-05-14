package com.liaojun.component.base.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ChamIt-001 on 2017/11/21.
 */
public class ExcelUtil {

    public static HSSFWorkbook getWorkbook(List<Map<String,Object>> exportData, Map<String,String> rowMapper) {
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet();
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
        //给表头赋值
        Set<String> keys = rowMapper.keySet();
        int i=0;
        for(String key:keys){
            HSSFCell cell = row.createCell((short) i);
            cell.setCellValue(rowMapper.get(key).toString());
            cell.setCellStyle(style);
            i++;
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        for (int j = 0; j < exportData.size(); j++)   {
            row = sheet.createRow((int) j + 1);
            // 第四步，创建单元格，并设置值
            Map<String, Object> rows = new HashMap<String,Object>();
            rows = exportData.get(j);
            Set<String> exportkeys=rowMapper.keySet();
            int a=0;
            for(String exportkey:exportkeys){
                row.createCell((short) a).setCellValue(rows.get(exportkey) == null ? "" : rows.get(exportkey).toString());
                a++;
            }
        }
        return wb;
    }

    /**
     * 读取第一个sheet的数据
     * @param inputStream
     * @return
     */
    public static List<Map<String,String>> getFirstSheetData(InputStream inputStream) {
        Workbook workbook = ExcelUtil.getWorkBook(inputStream);
        return getSetFromWorkbook(workbook).entrySet().iterator().next().getValue();
    }

    /**
     * 从输入流读取excel
     * @param inputstream
     * @return
     * @throws Exception
     */
    public static Workbook getWorkBook(InputStream inputstream) {
        Object book = null;
        if(!((InputStream)inputstream).markSupported()) {
            inputstream = new PushbackInputStream((InputStream)inputstream, 8);
        }

        try {
            book = WorkbookFactory.create(inputstream);
        } catch (IOException ioe){
            ioe.printStackTrace();
        } catch (InvalidFormatException ife){
            ife.printStackTrace();
        }

        return (Workbook) book;
    }

    /**
     * 取单元格的值
     * @param cell 单元格对象
     * @param treatAsStr 为true时，当做文本来取值
     * @return
     */
    public static String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }
        if (treatAsStr) {
            //设置为文本来读取
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        if(cell.getCellType() != Cell.CELL_TYPE_STRING && org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date theDate = cell.getDateCellValue();
            return sdf.format(theDate);
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue()).trim();
        }
    }

    /**
     * Workbook转Collection
     * @param workbook
     * @return
     */
    public static Map<Object,List<Map<String,String>>> getSetFromWorkbook(Workbook workbook){
        Map<Object,List<Map<String,String>>> result = new HashMap<Object,List<Map<String,String>>>();
        try {
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();
                if(lastRowIndex == 0){
                    continue;
                }
                // 读取首行 即,表头
                List<String> headList = new ArrayList<String>();
                Row firstRow = sheet.getRow(firstRowIndex);
                for (int i = firstRow.getFirstCellNum(); i < firstRow.getLastCellNum(); i++) {
                    Cell cell = firstRow.getCell(i);
                    String cellValue = getCellValue(cell, false);
                    headList.add(cellValue);
                }

                // 读取数据行
                List<Map<String,String>> sheetRows = new ArrayList<Map<String,String>>();
                for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                    Row currentRow = sheet.getRow(rowIndex);// 当前行
                    int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
                    int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
                    Map<String,String> rowMap = new HashMap<String,String>();
                    for (int columnIndex = firstColumnIndex,sysIndex = 0; columnIndex < lastColumnIndex; columnIndex++,sysIndex++) {
                        Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
                        String currentCellValue = getCellValue(currentCell, false);// 当前单元格的值
                        rowMap.put(headList.get(sysIndex),currentCellValue);
                    }
                    if(lastColumnIndex < headList.size()){
                        for(int patchIndex = lastColumnIndex; patchIndex < headList.size(); patchIndex ++ ){
                            rowMap.put(headList.get(patchIndex),null);
                        }
                    }
                    sheetRows.add(rowMap);
                }

                result.put(sheet.getSheetName(), sheetRows);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            return result;
        }
    }
}
