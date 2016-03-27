package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountLoginPage {
	private static WebElement element = null; 
	
	public static WebElement txtField_userName(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@id='log']"));
		return element;
	}
	
	public static WebElement txtField_password(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@id='pwd']"));
		return element;
	}
	
	public static WebElement btn_login(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@id='login']"));
		return element;
	}
	
	public static void login(WebDriver driver, String user, String password) throws InterruptedException
	{
		txtField_userName(driver).sendKeys(user);
		txtField_password(driver).sendKeys(password);
		// scroll login button in view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn_login(driver));	
		btn_login(driver).click();
	}

}
