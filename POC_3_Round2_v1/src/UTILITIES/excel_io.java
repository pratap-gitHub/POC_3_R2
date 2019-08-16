package UTILITIES;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel_io {
	
	public data read_excel(int i, String sheet)
	{
		data obj = new data();
		try
		{
			File f = new File("data.xlsx");
			FileInputStream fis = new FileInputStream(f);
			
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet("Sheet1");
			XSSFRow row = sh.getRow(i);
			XSSFCell cell_category = row.getCell(1);
			
			obj.category=cell_category.getStringCellValue();
			XSSFCell cell_price_lr = row.getCell(2);
			
			obj.price_lr = String.valueOf(cell_price_lr.getNumericCellValue());
			XSSFCell cell_price_hr = row.getCell(3);
			
			obj.price_hr = String.valueOf(cell_price_hr.getNumericCellValue());
			XSSFCell cell_number = row.getCell(4);
			
			obj.number = String.valueOf((int)cell_number.getNumericCellValue());
			
			
			
			
			
			
			for(int c=0;c<Integer.parseInt(obj.number);c++)
			{
				XSSFRow row_2 = sh.getRow(i+c);
				
				XSSFCell cell_bookname = row_2.getCell(5);
				XSSFCell cell_bookprices = row_2.getCell(6);
				
				obj.book_names.add(cell_bookname.getStringCellValue());
				obj.book_prices.add(String.valueOf(cell_bookprices.getNumericCellValue()));
			}
			
			return obj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return obj;
	}
}
