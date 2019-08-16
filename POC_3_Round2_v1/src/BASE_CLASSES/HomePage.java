package BASE_CLASSES;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver dr;
	Logger log;
	
	@FindBy(xpath="/html/body/table[2]/tbody/tr/td/a[2]")
	WebElement search_btn;
	
	public HomePage(WebDriver dr, Logger log)
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
	
	
	public String return_title()
	{
		 WebDriverWait wt = new WebDriverWait(dr,10);                             
		 wt.until(ExpectedConditions.elementToBeClickable(search_btn));
		
		 return dr.getTitle();
	}
	public void click_search()
	{
		search_btn.click();
	}
}
