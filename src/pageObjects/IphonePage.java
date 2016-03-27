package pageObjects;

import java.text.NumberFormat;
import java.text.ParseException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Add comments

public class IphonePage {
	private static WebElement element = null;
	
	// Add Wait and exception handling
	
	//"//a[text()='Apple iPhone 4S 16GB SIM-Free – Black']/../..//input[@name='Buy']"
	public static WebElement btn_addToCart(WebDriver driver, String iPhoneModel)
	{
		element = driver.findElement(By.xpath("//a[text()='" + iPhoneModel + "']/../..//input[@name='Buy']"));
		return element;
	}
	
	public static WebElement label_currentPrice(WebDriver driver, String iPhoneModel)
	{
		element = driver.findElement(By.xpath("//a[text()='" + iPhoneModel + "']/../..//span[contains(@class,'currentprice pricedisplay product_price')]"));
		return element;
	}
	
	public static double get_currentPrice(WebDriver driver, String iPhoneModel) throws ParseException
	{
		element = label_currentPrice(driver, iPhoneModel);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		Number number = format.parse(element.getText());
		
		return number.doubleValue();
	}
	
	public static WebElement btn_goToCheckout(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//a[@class='go_to_checkout']"));
		return element;
	}
	
	public static void acceptAlert(WebDriver driver) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 15, 100);
			wait.until(ExpectedConditions.alertIsPresent());
			
		Alert confirmAlert = driver.switchTo().alert();
		confirmAlert.accept();
	}
}

