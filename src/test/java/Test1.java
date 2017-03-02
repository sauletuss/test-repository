import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {

	private WebDriver driver;

	public boolean isStickerPresent(WebElement form, By locator){
		return form.findElements(locator).size() == 1;
	}

	@Before
	public void start() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		driver.get("http://litecart.stqa.ru/");
		driver.findElements(By.cssSelector("li.product.column.shadow.hover-light"));
		for (int i = 0; i < driver.findElements(By.cssSelector("li.product.column.shadow.hover-light")).size(); i++){

			WebElement form = driver.findElements(By.cssSelector("li.product.column.shadow.hover-light")).get(i);
			assertTrue(isStickerPresent(form, By.cssSelector("div.sticker")));
		}

	}

	@After
	public void stop() {
		driver.quit();
	}

}

