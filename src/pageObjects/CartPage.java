package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	private static WebElement element = null;
	
	public static WebElement btn_remove(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@value='Remove']"));
		return element;
	}
	
	public static WebElement btn_continue(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//span[text()='Continue']"));
		return element;
	}

}
