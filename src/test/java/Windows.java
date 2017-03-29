import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Windows {
	
	private WebDriver driver;
		
	@Before
 	public void start() {
 		driver = new ChromeDriver();
 	}

	@Test
	public void test() throws InterruptedException {
		driver.get("http://litecart.stqa.ru/admin/?app=countries&doc=countries");
		driver.findElement(By.name("username")).sendKeys("admin");
 		driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
 		driver.findElement(By.name("login")).click();
 		
 		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
 		int num = driver.findElements(By.cssSelector("i.fa.fa-external-link")).size();
 		for (int i = 0; i<num; i++){
 			driver.findElements(By.cssSelector("i.fa.fa-external-link")).get(i).click();
 			String mainWindow = driver.getWindowHandle();
 			Set<String> windows = driver.getWindowHandles();
 			WebDriverWait wait = new WebDriverWait(driver, 20/*seconds*/);
 			for (String newWindow : windows) {
 				if (newWindow.compareTo(mainWindow) != 0) {
 		 			driver.switchTo().window(newWindow);
 		 			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
 		 			driver.close();
 				}
 			}

 			driver.switchTo().window(mainWindow);
 		}
	}
		
@After
	public void stop() {
		driver.quit();
	}

}
