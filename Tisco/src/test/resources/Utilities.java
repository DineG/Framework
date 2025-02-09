import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utilities {

    public static FileInputStream fi;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static FileOutputStream fo;

    public static int getRowCount(String filePath, String sheetName) throws IOException {

        fi = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }
    public static int getCellCount(String filePath, String sheetName, int r) throws IOException {
        fi = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(r);
        int cellCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }

    public static String getCellData(String filePath, String sheetName, int r, int c) throws IOException {

        fi = new FileInputStream(filePath);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(r);
        cell = row.getCell(c);

        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }

        wb.close();
        fi.close();
        return data;

    }





}
