package zadanie19;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends Page{

	public ProductPage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	private int quantity;
	
	public void addProductToBasket() {
	
		WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		List<WebElement> isSale = driver.findElements(By.cssSelector("td.options select"));

		if (isSale.size() != 0) {
			Select dropdown = new Select(driver.findElement(By.cssSelector("td.options select")));
			dropdown.selectByVisibleText("Small"); 
		}
		
		
		driver.findElement(By.name("add_cart_product")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.quantity"), Integer.toString(quantity+1)));

	}
	
	public void setQuantity(int x) { 
		quantity = x;
	}

}
