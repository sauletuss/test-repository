import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NewUserRegistration {
	
	private WebDriver driver;

	@Before
	public void start() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void registrationOfNewUser() throws InterruptedException {
		driver.get("http://litecart.stqa.ru/");
		driver.findElement(By.cssSelector("div#box-account-login a")).click();
		driver.findElement(By.name("firstname")).sendKeys("Saule");
		driver.findElement(By.name("lastname")).sendKeys("Test");
		driver.findElement(By.name("address1")).sendKeys("Test Address");
		driver.findElement(By.name("postcode")).sendKeys("12345");
		driver.findElement(By.name("city")).sendKeys("Test City");
		Select dropdown = new Select(driver.findElement(By.cssSelector("select.select2-hidden-accessible")));
		dropdown.selectByVisibleText("United States");
		Select dropdownZoneCode = new Select(driver.findElement(By.cssSelector("select[name =zone_code]")));
		dropdownZoneCode.selectByVisibleText("Alaska");
        Random r = new Random(System.currentTimeMillis());
        String mail = "saule_"+r.nextInt(1000)+"@test.test";
        String pass = "123456";
		driver.findElement(By.name("email")).sendKeys(mail);
		driver.findElement(By.name("phone")).sendKeys("+777712345678");
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.name("confirmed_password")).sendKeys(pass);
		driver.findElement(By.name("create_account")).click();
		Thread.sleep(1000);
        driver.findElements(By.cssSelector(".box ul.list-vertical li a")).get(4).click();
		Thread.sleep(1000);
		driver.findElement(By.name("email")).sendKeys(mail);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
		Thread.sleep(1000);
        driver.findElements(By.cssSelector(".box ul.list-vertical li a")).get(4).click();
	}
	
	@After
	public void stop() {
		driver.quit();
	}

}
