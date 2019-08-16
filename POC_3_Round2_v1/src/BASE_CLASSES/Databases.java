package BASE_CLASSES;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import UTILITIES.Product;

public class Databases {

	WebDriver dr;
	
	By noOfProd= By.xpath("/html/body/table[5]/tbody/tr/td");
	
	String[] pname= {"Web Database Development","MySQL","MySQL and mSQL","Beginning ASP Databases","Oracle8i Web Development"};
	String[] pprice= {"$39.99","$39.99","$27.96","$39.99","$41.99"};
	
	public Databases(WebDriver dr)
	{
		this.dr= dr;
	}
	
	
	public String testNumberOfProds()		//ok
	{
		//System.out.println("A...");
		
		WebDriverWait wt = new WebDriverWait(dr,10);                             
		wt.until(ExpectedConditions.elementToBeClickable(noOfProd));

		String tmp= dr.findElement(noOfProd).getText();
		
		//System.out.println(ar);
		
		int i1=tmp.indexOf(" products found");
		String ar= tmp.substring(i1-1, i1);
		//System.out.println(np+">"+np.length());
		
		return ar;
		
	}

	public ArrayList<Product> testProductsList() 
	{

		ArrayList<WebElement> RowElements = (ArrayList)dr.findElements(By.xpath("//*[@class='Row']"));

		int lenRowElements= RowElements.size();
		
		ArrayList<Product> actualResult= new ArrayList<Product>();
		

		
		
		for(int i=1;i<=lenRowElements;i++)
		{
			
			String xp ="/html/body/table[5]/tbody/tr/td/table[2]/tbody/tr["+i+"]/td[2]/b/a";
			String xp2 ="/html/body/table[5]/tbody/tr/td/table[2]/tbody/tr["+i+"]/td[2]";
			
			
			
			
			if((i%2)!=0) 
			{
				
				WebDriverWait wt = new WebDriverWait(dr,10);                             
				wt.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table[5]/tbody/tr/td/table[2]/tbody/tr["+i+"]/td[2]")));
				
				
				

				String apname= dr.findElement(By.xpath(xp)).getText();
				String ar= dr.findElement(By.xpath(xp2)).getText();
				//System.out.println(ar);
				//int indx= ar.indexOf("Price");
				
				String aprice= ar.substring( ar.indexOf("$"));
				//System.out.println(apname+","+aprice);
				actualResult.add(new Product(apname,aprice));
			}
		}
		
		return actualResult;
			
	}
	
	public ArrayList<Product> getExpResList()
	{
		ArrayList<Product> expList= new ArrayList<Product>();
		
		for(int i=0;i<pname.length;i++)
		{
			Product p= new Product(pname[i], pprice[i]);
			expList.add(p);
		}
		
		return expList;
	}
	
	
	
	
	
	
	
	
}
