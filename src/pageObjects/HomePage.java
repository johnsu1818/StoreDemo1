package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	private static WebElement element = null;
	
	public static WebElement lnk_myAccount(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//a[@title='My Account']"));
		return element;
	}
	
	public static WebElement  menu_ProductCategory(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Product Category"));
		return element;
	}
	
	public static void goTo_IphonePage(WebDriver driver)
	{
		WebElement menu = menu_ProductCategory(driver);
		//Move mouse pointer on Product Category DropDown Menu
		Actions actions = new Actions(driver);
		actions.moveToElement(menu).perform();
		driver.findElement(By.linkText("iPhones")).click();
	}
	

}
