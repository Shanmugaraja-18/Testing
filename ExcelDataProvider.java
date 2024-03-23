package Sample.Sample;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataProvider {

    public static Object[][] getTestData(String filePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCnt = sheet.getLastRowNum();
        int colCnt = sheet.getRow(0).getLastCellNum();

        List<Object[]> testData = new ArrayList<>();

        for (int i = 1; i <= rowCnt; i++) {
            Row row = sheet.getRow(i);
            Object[] rowData = new Object[colCnt];
            for (int j = 0; j < colCnt; j++) {
                Cell cell = row.getCell(j);
                rowData[j] = cell.getStringCellValue(); 
            }
            testData.add(rowData);
        }

        workbook.close();
        fileInputStream.close();

        return testData.toArray(new Object[0][0]);
    }
}

