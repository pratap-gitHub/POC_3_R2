package TESTING_TESTS;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BASE_CLASSES.HomePage;



public class test1 {
	
	WebDriver dr;
	Logger log;
	HomePage homepage;
	
	
	@BeforeClass
	public void launch_browser()
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		dr=new ChromeDriver();
		
		dr.get("http://examples.codecharge.com/Store/Default.php");
		
		log = Logger.getLogger("devpinoyLogger");
	}
	

	
	@Test(priority=0)
	public void verify_title() 
	{
	
		homepage = new HomePage(dr,log);
	    
		String ar = homepage.return_title();
	    String er = "Online Bookstore";
	   
	    if(ar.compareTo(er)!=0)
		  homepage.create_log("verify_title",er,ar,"FAIL");
	      
	    
	    Assert.assertEquals(er, ar);
	    homepage.create_log("verify_title",er,ar,"PASS");
	}
	
	

	  @Test(dependsOnMethods="verify_title")
	  public void go_to_search()
	  {
		  homepage.click_search();
		  homepage.create_log("go_to_Search", "", "", "");
	  }
}
