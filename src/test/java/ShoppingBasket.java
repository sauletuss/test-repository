import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class ShoppingBasket {
	private WebDriver driver;

public void addProduct() {
	WebElement numOfItems = driver.findElement(By.cssSelector("a.content"));
	System.out.println(numOfItems.getAttribute("textContent"));
	driver.findElement(By.cssSelector("li.product.column.shadow.hover-light")).click();
	WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
	wait.until(presenceOfElementLocated(By.name("add_cart_product")));
	driver.findElement(By.name("add_cart_product")).click();
	wait.until(ExpectedConditions.stalenessOf(numOfItems));

}

	@Before
	public void start() {
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void test() throws InterruptedException {
		driver.get("http://litecart.stqa.ru/");
		addProduct();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div#logotype-wrapper a")).click();
		Thread.sleep(2000);
		addProduct();
		//driver.findElement(By.cssSelector("div#logotype-wrapper a")).click();
		//addProduct();
		//driver.findElement(By.cssSelector("div#logotype-wrapper a")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#cart .link")).click();
	}
	
	@After
	public void stop() {
		driver.quit();
	}

}