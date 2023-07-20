package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite extends BaseClass {

	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static FileOutputStream fout;
	static XSSFRow row;
	
	public static String ReadExcel() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + ".\\src\\main\\java\\Datatables\\input.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		row = sheet.getRow(0);
		XSSFCell cell = row.getCell(0);
		String value = cell.getStringCellValue();
		return value;
	}


	public static void writeExcel(String[] Titles, String[] Prices, String[] Totalprices) throws Exception {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("HOTEL DETAILS");

		// Column Heading
		row = sheet.createRow(0);
		row.createCell(0).setCellValue("Holiday Homes");
		row.createCell(1).setCellValue("Price per Night");
		row.createCell(2).setCellValue("Total Price");

		// Storing extracted data in excel
		for (int i = 0; i < 3; i++) {
			row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(Titles[i]);
			row.createCell(1).setCellValue(Prices[i]);
			row.createCell(2).setCellValue(Totalprices[i]);
			sheet.autoSizeColumn(i);
		}
		FileOutputStream writefile = new FileOutputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\Datatables\\HotelDetails.xlsx");
		workbook.write(writefile);
		writefile.close();
		workbook.close();
	}

	public static void writeExcelCruise(String[] list, String launchedyear, String languages) throws Exception {
		workbook = new XSSFWorkbook();
		// Fetching excel file from directory
		sheet = workbook.createSheet("Cruise Details");
		sheet.createRow(0).createCell(0).setCellValue("CRUISE DETAILS");
		sheet.createRow(1).createCell(0).setCellValue(list[0]);
		sheet.createRow(2).createCell(0).setCellValue(list[1]);
		sheet.createRow(3).createCell(0).setCellValue(launchedyear);
		sheet.createRow(4).createCell(0).setCellValue(languages);

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(4);
		FileOutputStream writefile2 = new FileOutputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\Datatables\\cruise.xlsx");
		
		workbook.write(writefile2);
		writefile2.close();
		workbook.close();
		
	}
}
