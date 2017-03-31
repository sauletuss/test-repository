package zadanie19;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends Page{

	public CartPage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	public void openCart() {
		WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
		driver.findElement(By.cssSelector("#cart .link")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
	}
	
	public void removeProducts() {
		int num = driver.findElements(By.cssSelector("ul.shortcuts li a")).size();
		for (int j = 0; j < num; j++){
			
			if (j == num-1) {
				driver.findElement(By.name("remove_cart_item")).click();
				WebElement box = driver.findElement(By.cssSelector("div #box-checkout-summary"));
		 		wait.until(stalenessOf(box));
		 		if (driver.findElements(By.cssSelector("td.item")).size() == 0) {
		 			System.out.println("All products have been removed");
		 		}

			}
			else {
			int numOfItems = driver.findElements(By.cssSelector("td.item")).size();
			WebElement currentItemToRemove = driver.findElements(By.cssSelector("ul.shortcuts li a")).get(0);
			WebElement box = driver.findElement(By.cssSelector("div #box-checkout-summary"));
			currentItemToRemove.click();
	 		driver.findElement(By.name("remove_cart_item")).click();
	 		wait.until(stalenessOf(box));
	 		if (driver.findElements(By.cssSelector("td.item")).size() < numOfItems) {
	 			System.out.println("Product has been removed");
	 		}
			}

		}

	}

}
