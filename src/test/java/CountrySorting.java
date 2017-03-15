import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CountrySorting {

	private WebDriver driver;


	public boolean compareCountries(String a, String b){ 
		if (a.compareTo(b) <= 0) return true;
		else
			return false;
	}

	@Before
	public void start() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void coutriesShouldHaveAlphabeticalOrder() throws InterruptedException {
		driver.get("http://litecart.stqa.ru/admin/?app=countries&doc=countries");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
		driver.findElement(By.name("login")).click();
		int num = driver.findElements(By.cssSelector("tr.row")).size();
		for (int i = 0; i<num-1; i++){
			WebElement form = driver.findElements(By.cssSelector("tr.row")).get(i);
			WebElement formNext = driver.findElements(By.cssSelector("tr.row")).get(i+1);
			String country = form.findElements(By.cssSelector("td")).get(4).getAttribute("textContent");
			String countryNext = formNext.findElements(By.cssSelector("td")).get(4).getAttribute("textContent");
			assertTrue(compareCountries(country, countryNext));
		}

		ArrayList<String> countriesWithZones = new ArrayList<String>();

		for (int i = 0; i<num; i++){
			WebElement form = driver.findElements(By.cssSelector("tr.row")).get(i); 
			if (form.findElements(By.cssSelector("td")).get(5).getAttribute("textContent").compareTo("0") != 0) {
				countriesWithZones.add(form.findElements(By.cssSelector("td")).get(3).getAttribute("textContent"));
			}
		}

		for (int j = 0; j<countriesWithZones.size(); j++){
			System.out.println(countriesWithZones.get(j));
			driver.get("http://litecart.stqa.ru/admin/?app=countries&doc=edit_country&country_code="+countriesWithZones.get(j));

			int numOfZones = driver.findElements(By.cssSelector("table#table-zones tbody tr")).size();
			for (int i = 1; i<numOfZones-2; i++){
				WebElement form = driver.findElements(By.cssSelector("table#table-zones tbody tr")).get(i);
				WebElement formNext = driver.findElements(By.cssSelector("table#table-zones tbody tr")).get(i+1);
				String country = form.findElements(By.cssSelector("td")).get(2).getAttribute("textContent");
				String countryNext = formNext.findElements(By.cssSelector("td")).get(2).getAttribute("textContent");
				assertTrue(compareCountries(country, countryNext));
			}


			driver.findElement(By.name("cancel")).click();
		} 

		driver.get("http://litecart.stqa.ru/admin/?app=geo_zones&doc=geo_zones");
		int numOfCountries = driver.findElements(By.cssSelector("tr.row")).size();
		for (int i = 0; i<numOfCountries; i++){
			WebElement form = driver.findElements(By.cssSelector("tr.row")).get(i); 
			form.findElements(By.cssSelector("a")).get(0).click();
			Thread.sleep(3000);
			int numOfGeoZones = driver.findElements(By.cssSelector("table.dataTable tbody tr")).size();

			for (int j = 1; j<numOfGeoZones-2;j++){
				WebElement formOfZones = driver.findElements(By.cssSelector("table.dataTable tbody tr")).get(j);
				WebElement formOfZonesNext = driver.findElements(By.cssSelector("table.dataTable tbody tr")).get(j+1);
				WebElement selectElem = formOfZones.findElements(By.tagName("select")).get(1);
				Select select = new Select(selectElem);
				WebElement selectElemNext = formOfZonesNext.findElements(By.tagName("select")).get(1);
				Select selectNext = new Select(selectElemNext);
				String country = select.getFirstSelectedOption().getText();
				String countryNext = selectNext.getFirstSelectedOption().getText();
				assertTrue(compareCountries(country, countryNext));
			}

			driver.findElement(By.name("cancel")).click();
		}

	}


	@After
	public void stop() {
		driver.quit();
	}


}