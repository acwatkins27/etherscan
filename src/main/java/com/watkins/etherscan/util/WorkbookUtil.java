package com.watkins.etherscan.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkbookUtil {

    static final String SHEET_NAME = "Query Result";

    public Workbook writeObjects2ExcelFile(JSONObject response) throws IOException {
        JSONArray results = response.getJSONArray("result");
        Iterator<String> columns = new JSONObject(results.get(0).toString()).keys();

        Workbook workbook = new XSSFWorkbook();

        CreationHelper createHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet(SHEET_NAME);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Row for Header
        Row headerRow = sheet.createRow(0);

        // Header
        createHeader(columns, headerCellStyle, headerRow);

        // CellStyle for Age
        CellStyle numberCellStyle = workbook.createCellStyle();
        numberCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

        createCells(results, sheet, numberCellStyle);

        /*FileOutputStream fileOut = new FileOutputStream("result.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();*/
        return workbook;
    }

    private void createCells(JSONArray results, Sheet sheet, CellStyle numberCellStyle) {
        int rowIdx = 1;


        List<List<String>> listOfResults = new ArrayList<>();
        getListOfResults(results, listOfResults);

        for (List<String> resultList : listOfResults) {
            Row row = sheet.createRow(rowIdx++);
            AtomicInteger columnIndex = new AtomicInteger();
            columnIndex.set(0);
            resultList.forEach(singleResult -> {
                try {
                    double value = Double.parseDouble(singleResult);
                    Cell ageCell = row.createCell(columnIndex.getAndIncrement());
                    ageCell.setCellValue(value);
                    ageCell.setCellStyle(numberCellStyle);
                } catch (NumberFormatException nfe) {
                    row.createCell(columnIndex.getAndIncrement()).setCellValue(singleResult);
                }
            });
        }
    }

    private void createHeader(Iterator<String> columns, CellStyle headerCellStyle, Row headerRow) {
        int columnIndex = 0;
        while (columns.hasNext()) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue(columns.next());
            cell.setCellStyle(headerCellStyle);
            columnIndex++;
        }
    }

    public static void getListOfResults(JSONArray results, List<List<String>> listOfResults) {
        results.forEach(result -> {
            JSONObject jsonResult = new JSONObject(result.toString());
            Iterator<String> keys = jsonResult.keys();
            List<String> values = new ArrayList<>();
            while(keys.hasNext()) {
                values.add((String) jsonResult.get(keys.next()));
            }
            listOfResults.add(values);
        });
    }
}
