package seleniumtest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class seleniumTest {
	
	private WebDriver driver;
	
	@Before
	public void start() {
		driver = new FirefoxDriver();
	}
	
	@Test
	public void test() {
		driver.get("http://localhost/litecart/admin/");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("login")).click();
	}
	
	@After
	public void stop() {
		driver.quit();
	}

}
