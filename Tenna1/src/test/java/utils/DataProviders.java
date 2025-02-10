package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String[][] getLoginData() throws IOException {
        String path = "./testData/testTOO.xlsx";
        ExcelUtility xlutility = new ExcelUtility(path);

        int xlRowCount = xlutility.getRowCount("loginData");
        int xlCellCount = xlutility.getCellCount("loginData", 1);

        String[][] data = new String[xlRowCount][xlCellCount];
        for (int r = 1; r<=xlRowCount; r++) {
            for (int c=0; c<xlCellCount; c++) {
                data[r-1][c] = xlutility.getCellData("loginData", r,c);
            }
        }
        return data;
    }

}
