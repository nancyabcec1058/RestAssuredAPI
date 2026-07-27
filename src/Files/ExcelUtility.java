package Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    Workbook workbook;
    Sheet sheet;

    public ExcelUtility(String path) throws Exception {

        FileInputStream fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet("Sheet1");
        fis.close();
    }

    // Read data from Excel
    public String getCellData(int row, int col) {

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(sheet.getRow(row).getCell(col));
    }

    // Write data to Excel
    public void setCellData(int row, int col, String value) throws Exception {

        Row r = sheet.getRow(row);

        if (r == null) {
            r = sheet.createRow(row);
        }

        Cell cell = r.getCell(col);

        if (cell == null) {
            cell = r.createCell(col);
        }

        cell.setCellValue(value);

        FileOutputStream fos = new FileOutputStream(
                "C:\\Users\\nancy\\eclipse-workspace\\RestAssuredPractice\\Library.xlsx");

        workbook.write(fos);

        fos.close();
    }

    public void closeWorkbook() throws Exception {
        workbook.close();
    }
}

