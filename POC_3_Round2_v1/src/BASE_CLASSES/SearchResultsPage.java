package BASE_CLASSES;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchResultsPage {
	WebDriver dr;
	Logger log = Logger.getLogger("devpinoyLogger");
	
	@FindBy(xpath="/html/body/table[5]/tbody/tr/td")
	WebElement page_txt;
	
	@FindBy(xpath="/html/body/table[5]/tbody/tr/td/table[1]/tbody/tr/td/form/table[2]/tbody/tr[3]/td/input")
	WebElement search_btn;
	
	public SearchResultsPage(WebDriver dr, Logger log)
	{
		this.dr=dr;
		this.log=log;
		PageFactory.initElements(dr,this);
	}
	public void create_log(String meth_name, String exp_res, String act_res, String test_res)
	{
		if(test_res.compareTo("PASS")==0)
		{
			if(exp_res=="")
				log.info("Method "+ meth_name+" invoked \n"+"TC_ID: TESTING_TESTS.test1#"+meth_name+"\n");
			else
				log.info("Method "+ meth_name+" invoked \n"+"TC_ID: TESTING_TESTS.test1#"+meth_name+"\nExpected Result: "+exp_res+"\nActual Result: "+act_res+"\nTest Result: "+test_res+"\n");
	
		}
		else {
			if(exp_res=="")
				log.info("Method "+ meth_name+" invoked \n"+"TC_ID: TESTING_TESTS.test1#"+meth_name+"\n");
			else
				log.info("Method "+ meth_name+"\n"+"TC_ID: TESTING_TESTS.test1#"+meth_name+"\nExpected Result: "+exp_res+"\nActual Result: "+act_res+"\nTest Result: "+test_res+"\n");
		}
	}
	public String return_number()
	{
		 WebDriverWait wt = new WebDriverWait(dr,10);                             
		 wt.until(ExpectedConditions.elementToBeClickable(search_btn));
		 String actual_txt = page_txt.getText();
		 int index = actual_txt.indexOf(":");
		 int num = index+2;
		 String no_of_prod = actual_txt.substring(num, actual_txt.indexOf('p')-1);
		 return no_of_prod;
	}
	public String return_book_name(int i)
	{
		 WebDriverWait wt = new WebDriverWait(dr,10);                             
		 wt.until(ExpectedConditions.elementToBeClickable(search_btn));
		String book_name, row_txt;
		int c=i*2+1;
		row_txt = dr.findElement(By.xpath("/html/body/table[5]/tbody/tr/td/table[2]/tbody/tr["+c+"]/td[2]")).getText();
		book_name = row_txt.substring(0, row_txt.indexOf(":")-6);
			//book_price = row_txt.substring(row_txt.indexOf("$")+1, row_txt.length());
		return book_name;
	}
	public String return_book_price(int i)
	{
		 WebDriverWait wt = new WebDriverWait(dr,10);                             
		 wt.until(ExpectedConditions.elementToBeClickable(search_btn));
		String book_price, row_txt;
		int c=i*2+1;
		row_txt = dr.findElement(By.xpath("/html/body/table[5]/tbody/tr/td/table[2]/tbody/tr["+c+"]/td[2]")).getText();
		//book_name = row_txt.substring(0, row_txt.indexOf(":")-6);
		book_price = row_txt.substring(row_txt.indexOf("$")+1, row_txt.length());
		return book_price;
	}
}
