package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountDetailPage {
	private static WebElement element = null;
	
	public static WebElement link_accountDetails(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//a[text()='Your Details']"));
		return element;
	}
	
//	public static WebElement btn_logout(WebDriver driver)
//	{
//		element = driver.findElement(By.xpath("//a[text()='Log out']"));
//		return element;
//	}
	
	public static WebElement btn_logout(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//a[text()='(Logout)']"));
		return element;
	}
	
	public static WebElement txtArea_address(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//textarea[@title='billingaddress']"));
		return element;
	}
	
	public static WebElement txtField_phone(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@title='billingphone']"));
		return element;
	}
	
	public static WebElement btn_saveProfile(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@value='Save Profile']"));
		return element;
	}

}
