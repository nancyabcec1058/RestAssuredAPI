import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTest {
	public static void main(String[] args) throws IOException {
	
		FileInputStream fis=new FileInputStream("C:\\Users\\nancy\\eclipse-workspace\\RestAssuredPractice\\RestAssured.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		int sheets=workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("Sheet1"))
			{
			XSSFSheet sheet=workbook.getSheetAt(i);
			Iterator<Row> rows=sheet.iterator();
			}
		}
		
	}

}
