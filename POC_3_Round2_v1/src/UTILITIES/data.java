package UTILITIES;

import java.util.ArrayList;

public class data {
	
	public String category;
	public String price_lr;
	public String price_hr;
	public String number;
	
	//String[] book_names = new String[4];
	//String[] book_prices = new String[4];
	
	public ArrayList<String> book_names;
	public ArrayList<String> book_prices;
	
	public data()
	{
		book_names=new ArrayList<String>();
		book_prices=new ArrayList<String>();
	}
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrice_lr() {
		return price_lr;
	}
	public void setPrice_lr(String price_lr) {
		this.price_lr = price_lr;
	}
	public String getPrice_hr() {
		return price_hr;
	}
	public void setPrice_hr(String price_hr) {
		this.price_hr = price_hr;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public ArrayList<String> getBook_names() {
		return book_names;
	}
	public void setBook_names(ArrayList<String> book_names) {
		this.book_names = book_names;
	}
	public ArrayList<String> getBook_prices() {
		return book_prices;
	}
	public void setBook_prices(ArrayList<String> book_prices) {
		this.book_prices = book_prices;
	}
	
	
	
}
