package zadanie19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShoppingBag {
	
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		MainPage mainPage = new MainPage(driver);
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);

		for (int i = 0; i < 3; i++) {
			mainPage.open();
			mainPage.openProduct();
			productPage.setQuantity(mainPage.getQuantity());
			productPage.addProductToBasket();
		}
		
		cartPage.openCart();
		cartPage.removeProducts();
		
		driver.quit();
	}

}
