package BASE_CLASSES;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	WebDriver dr;
	Logger log;
	
	@FindBy(xpath="/html/body/table[5]/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[1]/td[2]/select")
	WebElement category_list;
	
	@FindBy(xpath="/html/body/table[5]/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[3]/td/input")
	WebElement price_lr;
	
	@FindBy(xpath="/html/body/table[5]/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[4]/td/input")
	WebElement price_hr;
	
	@FindBy(xpath="/html/body/table[5]/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[7]/td/input")
	WebElement search_btn;
	
	public SearchPage(WebDriver dr, Logger log)
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
				log.info("Method "+ meth_name+" invoked \n"+"TC_ID: TESTING_TESTS.test2#"+meth_name+"\n");
			else
				log.info("Method "+ meth_name+" invoked \n"+"TC_ID: TESTING_TESTS.test2#"+meth_name+"\nExpected Result: "+exp_res+"\nActual Result: "+act_res+"\nTest Result: "+test_res+"\n");
	
		}
		else {
			if(exp_res=="")
				log.info("Method "+ meth_name+" invoked \n"+"TC_ID: TESTING_TESTS.test2#"+meth_name+"\n");
			else
				log.info("Method "+ meth_name+"\n"+"TC_ID: TESTING_TESTS.test2#"+meth_name+"\nExpected Result: "+exp_res+"\nActual Result: "+act_res+"\nTest Result: "+test_res+"\n");
		}
	}
	public void search_books(String category, String price_more_than, String price_less_than)
	{
		WebDriverWait wt = new WebDriverWait(dr,10);                             
		wt.until(ExpectedConditions.elementToBeClickable(search_btn));
		
		
		Select sel = new Select(category_list);
		sel.selectByVisibleText(category);
		
		price_lr.sendKeys(price_more_than);
		price_hr.sendKeys(price_less_than);
		
		search_btn.click();
	}



	public String verifyTitle() {
		
		String title= dr.getTitle();
		
		return title;
		
		
	}
}
