import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddNewProduct {

	private WebDriver driver;
	
	public boolean wasNewProductAdded(By locator, String name){
		int numOfProducts = driver.findElements(locator).size();
		if (name.compareTo(driver.findElements(locator).get(numOfProducts-1).findElements(By.cssSelector("td")).get(2).findElement(By.cssSelector("a")).getAttribute("textContent")) == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Before
	public void start() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void testNewProduct() throws InterruptedException, IOException {
		//¬ход в админку
		driver.get("http://localhost/litecart/admin/");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("login")).click();
		Thread.sleep(2000);
		//открыть меню Catalog
		driver.findElements(By.cssSelector("ul#box-apps-menu .name")).get(1).click();
		Thread.sleep(2000);
		//нажать кнопку "Add New Product"
		driver.findElements(By.cssSelector("a.button")).get(1).click();
		Thread.sleep(2000);
		//¬кладка General
		driver.findElement(By.name("status")).click();
		driver.findElement(By.name("name[en]")).sendKeys("Shower gel");
		driver.findElement(By.name("code")).sendKeys("sh001");
		driver.findElement(By.name("quantity")).sendKeys(Keys.HOME + "1");		
		File image = new File("ShowerGel.jpg");
		driver.findElement(By.name("new_images[]")).sendKeys(image.getAbsolutePath());
		//¬кладка Information
		driver.findElements(By.cssSelector("ul.index li a")).get(1).click();
		Thread.sleep(2000);
		Select dropdownManufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
		dropdownManufacturer.selectByVisibleText("ACME Corp.");
		driver.findElement(By.name("short_description[en]")).sendKeys("Shower gel yellow");
		driver.findElement(By.name("description[en]")).sendKeys("Description of yellow shower gel");
		//¬кладка Prices
		driver.findElements(By.cssSelector("ul.index li a")).get(3).click();
		Thread.sleep(2000);
		driver.findElement(By.name("purchase_price")).sendKeys(Keys.HOME + "2");
		Thread.sleep(1000);
		Select dropdownPurchasePriceCurrency = new Select(driver.findElement(By.name("purchase_price_currency_code")));
		dropdownPurchasePriceCurrency.selectByVisibleText("US Dollars");
		driver.findElement(By.name("prices[USD]")).sendKeys(Keys.HOME + "30");
		driver.findElement(By.name("save")).click();
		Thread.sleep(2000);
		//”бедитьс€, что новый продукт по€вилс€ в каталоге
		assertTrue(wasNewProductAdded(By.cssSelector("tr.row"), "Shower gel"));
		
	}

	@After
	public void stop() {
		driver.quit();
	}
}
