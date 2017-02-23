package seleniumtest;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class seleniumTest {

	@Test
	public void test() {
		WebDriver driver;
		driver = new FirefoxDriver();
		
		driver.get("http://google.com/");
		
		driver.quit();
	}

}
