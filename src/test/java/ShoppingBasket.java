import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;


public class ShoppingBasket {
	private WebDriver driver;
	
	
	public  boolean boxChanged(int numOfItems, By locator){     
		if (driver.findElements(locator).size() < numOfItems) return true;
		else
			return false;
	}
	
	@Before
	public void start() {
		driver = new ChromeDriver();
	}

	@Test
	public void test() {
		
		for (int i = 1; i < 4; i++){
			
		String s = i + "";
		driver.get("http://litecart.stqa.ru/");
		driver.findElement(By.cssSelector("li.product.column.shadow.hover-light a.link")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		List<WebElement> isSale = driver.findElements(By.cssSelector("td.options select"));

		if (isSale.size() != 0) {
			 Select dropdown = new Select(driver.findElement(By.cssSelector("td.options select")));
			 dropdown.selectByVisibleText("Small"); 
		}

		driver.findElement(By.name("add_cart_product")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.quantity"), s));

		driver.findElement(By.cssSelector("nav#breadcrumbs .list-horizontal li")).click();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
		driver.findElement(By.cssSelector("#cart .link")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		
		int num = driver.findElements(By.cssSelector("ul.shortcuts li a")).size();
		for (int j = 0; j < num; j++){
			
			if (j == num-1) {
				driver.findElement(By.name("remove_cart_item")).click();
			}
			else {
			int numOfItems = driver.findElements(By.cssSelector("td.item")).size();
			WebElement currentItemToRemove = driver.findElements(By.cssSelector("ul.shortcuts li a")).get(0);
			WebElement box = driver.findElement(By.cssSelector("div #box-checkout-summary"));
			currentItemToRemove.click();
	 		driver.findElement(By.name("remove_cart_item")).click();
	 		wait.until(stalenessOf(box));
	 		assertTrue(boxChanged(numOfItems, By.cssSelector("td.item")));
			}

		}

	}
	

	@After
	public void stop() {
		driver.quit();
	}

}