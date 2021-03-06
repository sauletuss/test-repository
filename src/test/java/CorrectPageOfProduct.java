import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class CorrectPageOfProduct {

	private WebDriver driver;

	public boolean correctRegularPrice(By locator){ 
		String textDecoration = driver.findElement(locator).getCssValue("text-decoration");
		String colorOfRegularPrice = driver.findElement(locator).getCssValue("color");
		if (((colorOfRegularPrice.compareTo("rgb(119, 119, 119)") == 0)||(colorOfRegularPrice.compareTo("rgb(102, 102, 102)") == 0)||(colorOfRegularPrice.compareTo("rgba(102, 102, 102, 1)") == 0)||(colorOfRegularPrice.compareTo("rgba(119, 119, 119, 1)") == 0))&&(textDecoration.compareTo("line-through") == 0)) return true;
		else
			return false;
	}

	public boolean correctCampaignPrice(By locator){ 

		String fontWeight = driver.findElement(locator).getCssValue("font-weight");
		String colorOfCampaignPrice = driver.findElement(locator).getCssValue("color");
		if (((colorOfCampaignPrice.compareTo("rgb(204, 0, 0)") == 0)||(colorOfCampaignPrice.compareTo("rgba(204, 0, 0, 1)") == 0))&&(fontWeight.compareTo("bold") == 0 || fontWeight.compareTo("900") == 0 || fontWeight.compareTo("700") == 0)) return true;
		else
			return false;
	}

	public boolean compareFontSizes(By locator1, By locator2){ 
		String fontSizeOfRegularPrice = driver.findElement(locator1).getCssValue("font-size");
		String fontSizeOfCampaignPrice = driver.findElement(locator2).getCssValue("font-size");

		if (fontSizeOfRegularPrice.compareTo(fontSizeOfCampaignPrice) < 0) return true;
		else
			return false;
	}

	public boolean compareValues(String a, String b){ 
		if (a.compareTo(b) == 0) return true;
		else
			return false;
	}

	@Before
	public void start() {
		//Select a driver
		//driver = new ChromeDriver();
		//driver = new InternetExplorerDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		driver.get("http://litecart.stqa.ru/");
		//get values of product on the main page
		String nameOfProduct = driver.findElement(By.cssSelector("div#box-campaigns div.name")).getAttribute("textContent");
		String regularPrice = driver.findElement(By.cssSelector("div#box-campaigns s.regular-price")).getAttribute("textContent");
		String campaignPrice = driver.findElement(By.cssSelector("div#box-campaigns strong.campaign-price")).getAttribute("textContent");

		assertTrue(compareFontSizes(By.cssSelector("div#box-campaigns s.regular-price"), By.cssSelector("div#box-campaigns strong.campaign-price"))); 
		assertTrue(correctRegularPrice(By.cssSelector("div#box-campaigns s.regular-price")));
		assertTrue(correctCampaignPrice(By.cssSelector("div#box-campaigns li.product .price-wrapper strong")));


		driver.findElement(By.cssSelector("div#box-campaigns a.link")).click();
		//compare values
		assertTrue(compareValues(nameOfProduct, driver.findElement(By.cssSelector("div#box-product h1.title")).getAttribute("textContent")));
		assertTrue(compareValues(regularPrice, driver.findElement(By.cssSelector("div#box-product s.regular-price")).getAttribute("textContent")));
		assertTrue(compareValues(campaignPrice, driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getAttribute("textContent")));

		assertTrue(compareFontSizes(By.cssSelector("div#box-product s.regular-price"), By.cssSelector("div#box-product strong.campaign-price"))); 
		assertTrue(correctRegularPrice(By.cssSelector("div#box-product s.regular-price")));
		assertTrue(correctCampaignPrice(By.cssSelector("div#box-product strong.campaign-price")));

	}

	@After
	public void stop() {
		driver.quit();
	}

}