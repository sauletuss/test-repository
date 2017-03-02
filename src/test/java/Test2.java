import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Test2 {


	private WebDriver driver;

	public boolean areElementsPresent(By locator){
		return driver.findElements(locator).size() > 0;
	}

	@Before
	public void start() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException {

		driver.get("http://litecart.stqa.ru/admin/");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
		driver.findElement(By.name("login")).click();

		driver.findElements(By.cssSelector("ul#box-apps-menu .name")).get(0).click();
		int num = driver.findElements(By.cssSelector("ul#box-apps-menu .name")).size();

		for(int j = 1 ; j < num;j++){
			List<WebElement> AllElements1 = driver.findElements(By.cssSelector("li#app-.selected ul.docs span.name"));
			System.out.println(AllElements1.size());

			for(int l = 0 ; l < AllElements1.size();l++){
				driver.findElements(By.cssSelector("li#app-.selected ul.docs span.name")).get(l).click();
				assertTrue(areElementsPresent(By.cssSelector("h1")));

			} 
			num = driver.findElements(By.cssSelector("ul#box-apps-menu .name")).size() + AllElements1.size();
			driver.findElements(By.cssSelector("ul#box-apps-menu .name")).get(j + AllElements1.size()).click();

		}
		

	}

	@After
	public void stop() {
		driver.quit();
	}
}