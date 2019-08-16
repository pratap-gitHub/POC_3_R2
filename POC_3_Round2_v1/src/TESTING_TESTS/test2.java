package TESTING_TESTS;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BASE_CLASSES.SearchPage;
import BASE_CLASSES.SearchResultsPage;

import UTILITIES.data;
import UTILITIES.excel_io;

public class test2 extends test1 {
	
	
	int j=-1;
	int i=-1;
	
	
	ArrayList<data> records;
	
	excel_io obj;
	
	SearchPage searchpage;
	SearchResultsPage searchresults;
	
	
	
	public data read_excel(int i, String sheet)
	{
		data obj = new data();
		try
		{
			File f = new File("data.xlsx");
			FileInputStream fis = new FileInputStream(f);
			
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet("Sheet1");
			XSSFRow row = sh.getRow(i);
			
			
			XSSFCell category = row.getCell(1);
			XSSFCell price_lr = row.getCell(2);
			XSSFCell price_hr = row.getCell(3);
			XSSFCell number = row.getCell(4);
			
			
			
			obj.category=category.getStringCellValue();
			obj.price_lr = String.valueOf(price_lr.getNumericCellValue());
			
			obj.price_hr = String.valueOf(price_hr.getNumericCellValue());
			
			obj.number = String.valueOf((int)number.getNumericCellValue());
			
			
			
			
			//fetching the book names and prices
			
			for(int c=0;c<Integer.parseInt(obj.number);c++)
			{
				XSSFRow row1 = sh.getRow(i+c);
				
				XSSFCell cell_bookname = row1.getCell(5);
				XSSFCell cell_bookprices = row1.getCell(6);
				
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
	
	
	@BeforeClass
	public void get_data()
	{
		records = new ArrayList<data>();
		
		records.add(read_excel(1,"Sheet1"));
	}
	
	/*
	
	  @Test(dependsOnMethods="go_to_search")
	  public void search() 
	  {
		  searchpage = new SearchPage(dr,log);
		  //searchpage.search_books(records.get(0).getCategory(), records.get(0).getPrice_lr(), records.get(0).getPrice_hr());
	  }
	  
	  */
	  
	  @Test(priority=0,dependsOnMethods="go_to_search")
	  public void testSearchTitle()
	  {
		  searchpage = new SearchPage(dr,log);
		  
		  String er= "Advanced Search";
		  String ar= searchpage.verifyTitle();
		  
		   if(ar.compareTo(er)!=0)
				  homepage.create_log("testSearchTitle",er,ar,"FAIL");
		   
		   Assert.assertEquals(ar, er);
		   
		   homepage.create_log("testSearchTitle",er,ar,"PASS");
		  
	  }
	  
	  @Test(priority=1, dependsOnMethods="go_to_search")
	  public void enterCategoryDetails() 
	  {
		  //searchpage = new SearchPage(dr,log);
		  searchpage.search_books(records.get(0).getCategory(), records.get(0).getPrice_lr(), records.get(0).getPrice_hr());
	  }	  
	  
	  
	  @Test (dependsOnMethods = "enterCategoryDetails")
	  public void verify_number()
	  {
		  searchresults = new SearchResultsPage(dr,log);
		  
		  String exp_no = "4";
		  String act_no = searchresults.return_number();
		  
		  if(act_no.compareTo(exp_no)!=0)
			  searchresults.create_log("verify_number", exp_no, act_no, "FAIL");
		  
		  Assert.assertEquals(act_no, exp_no);
		  searchresults.create_log("verify_number", exp_no,act_no,"PASS");
		  
	  }
	  
	  
	  
	  @Test (dependsOnMethods = "verify_number", dataProvider = "expected_book_names")
	  public void verify_books(String exp_book_name)
	  {
		  searchresults = new SearchResultsPage(dr,log);
		  i++;
		  String act_book_name = searchresults.return_book_name(i);
		  
		  if(act_book_name.compareTo(exp_book_name)!=0)
			  searchresults.create_log("verify_books", exp_book_name, act_book_name, "FAIL");
		  Assert.assertEquals(act_book_name, exp_book_name);
		  
		  
		  searchresults.create_log("verify_books", exp_book_name, act_book_name, "PASS");
	  }
	  
	  
	  
	  @Test (dependsOnMethods = "verify_number", dataProvider = "expected_book_prices")
	  public void verify_prices(String exp_book_price)
	  {
		  searchresults = new SearchResultsPage(dr,log);
		  j++;
		  String act_book_price = searchresults.return_book_price(j);
		  if(act_book_price.compareTo(exp_book_price)!=0)
			  searchresults.create_log("verify_prices", exp_book_price, act_book_price, "FAIL");
		  Assert.assertEquals(act_book_price, exp_book_price);
		  searchresults.create_log("verify_prices", exp_book_price, act_book_price, "PASS");
	  }

	  
	  
	  @DataProvider (name="expected_book_names")
	  public ArrayList<String> return_exp_books()
	  {
		  ArrayList<String> exp_names = new ArrayList<String>();
		  exp_names = records.get(0).getBook_names();
		  return exp_names;
	  }
	  
	  
	  
	  
	  @DataProvider (name="expected_book_prices")
	  public ArrayList<String> return_exp_prices()
	  {
		  ArrayList<String> exp_prices = new ArrayList<String>();
		  exp_prices = records.get(0).getBook_prices();
		  return exp_prices;
	  }
}