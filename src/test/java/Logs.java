import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Logs {

	private WebDriver driver;

	@Before
 	public void start() {
 		driver = new ChromeDriver();
 		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
 	}
	

	@Test
	public void test() {
		
		driver.get("http://litecart.stqa.ru/admin/?app=catalog&doc=catalog&category_id=1");
		driver.findElement(By.name("username")).sendKeys("admin");
 		driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
 		driver.findElement(By.name("login")).click();
 		
 		int numOfProducts = driver.findElements(By.cssSelector("tr.row")).size();
 		
 		for (int i = 3; i < numOfProducts; i++) {
 			driver.findElements(By.cssSelector("tr.row")).get(i).findElements(By.cssSelector("a")).get(0).click();
 	 		driver.findElement(By.name("cancel")).click();
 	 		System.out.println(driver.manage().logs().get("browser").getAll());
 		}
	}
	
	@After
	public void stop() {
		driver.quit();
	}

}
