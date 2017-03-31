package zadanie19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page{
	
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    public void open() {
    	
	driver.get("http://litecart.stqa.ru/");
	
    }
	
    public void openProduct() {
    	
	driver.findElement(By.cssSelector("li.product.column.shadow.hover-light a.link")).click();
	
    }  
    
    public int getQuantity(){ 
    
    	String s = driver.findElement(By.cssSelector(".quantity")).getAttribute("textContent");
    	int numOfProducts = Integer.parseInt(s);
    	return numOfProducts;
    	
    }
   
}
