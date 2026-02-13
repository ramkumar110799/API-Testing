package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class dataProviders {

	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;

	@DataProvider(name = "excelData")
	public Object[][] excelData() throws IOException{
		fis = new FileInputStream("C:\\Users\\TrafficLaunch\\eclipse-workspace\\PetStore\\datas\\datas.xlsx");

		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet("Sheet1");
		int rowNum = sheet.getLastRowNum();
		int colNum = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rowNum][colNum];
		DataFormatter dft = new DataFormatter();

		for(int i = 1; i<=rowNum ;i++) {
			XSSFRow row = sheet.getRow(i);
			for(int j= 0 ; j < colNum ; j++) {
				XSSFCell cell= row.getCell(j);
				
				data[i-1][j] = dft.formatCellValue(cell);

			}
		}
		
		workbook.close();
		fis.close();
		return data;

	}


	@DataProvider(name="ExcelUserNames")
	public Object[][] ExcelUserName() throws IOException {

		fis = new FileInputStream("C:\\Users\\TrafficLaunch\\eclipse-workspace\\PetStore\\datas\\datas.xlsx");

		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet("Sheet1");
		int rowNum = sheet.getLastRowNum();
		DataFormatter dft = new DataFormatter();
		
		Object[][] names = new Object[rowNum][1];
		for(int i = 1 ; i<=rowNum ; i++) {
			XSSFCell cell= sheet.getRow(i).getCell(1);
			
			names[i-1][0] =  dft.formatCellValue(cell);
			
		}
		workbook.close();
		fis.close();
		return names;
	}

}
