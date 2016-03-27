package pageObjects;

import java.text.NumberFormat;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
	private static WebElement element = null;
	
	public static WebElement txtField_emailAddress(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@title='billingemail']"));
		return element;
	}
	
	public static WebElement chkbox_sameAsBillingAddress(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@id='shippingSameBilling']"));
		return element;
	}
	
	public static void uncheck_chkbox_sameAsBillingAddress(WebDriver driver)
	{
		if (chkbox_sameAsBillingAddress(driver).isSelected())
			chkbox_sameAsBillingAddress(driver).click();
	}
	
	public static void check_chkbox_sameAsBillingAddress(WebDriver driver)
	{
		if (!chkbox_sameAsBillingAddress(driver).isSelected())
			chkbox_sameAsBillingAddress(driver).click();
	}
	
	public static WebElement txtField_billingFirstName(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@title='billingfirstname']"));
		return element;
	}
	
	public static WebElement txtField_billingLastName(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@title='billinglastname']"));
		return element;
	}
	
	public static WebElement txtField_billingAddress(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//textarea[@title='billingaddress']"));
		return element;
	}
	
	public static WebElement txtField_billingCity(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@title='billingcity']"));
		return element;
	}
	
	public static WebElement txtField_billingState(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@title='billingstate']"));
		return element;
	}
	
	public static WebElement txtField_billingCountry(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//select[@title='billingcountry']"));
		return element;
	}
	
	public static WebElement txtField_billingPhone(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@title='billingphone']"));
		return element;
	}
	
	public static WebElement label_totalShipping(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//tr[@class='total_price total_shipping']//span[@class='pricedisplay']"));
		return element;
	}
	
	public static double get_totalShipping(WebDriver driver) throws ParseException
	{
		element = label_totalShipping(driver);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		Number number = format.parse(element.getText());
		
		return number.doubleValue();
	}
	
	public static WebElement label_itemCost(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//tr[@class='total_price total_item']//span[@class='pricedisplay']"));
		return element;
	}
	
	public static double get_itemCost(WebDriver driver) throws ParseException
	{
		element = label_itemCost(driver);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		Number number = format.parse(element.getText());
		
		return number.doubleValue();
	}
	
	public static WebElement label_tax(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//tr[@class='total_price total_tax']//span[@class='pricedisplay']"));
		return element;
	}
	
	public static double get_tax(WebDriver driver) throws ParseException
	{
		element = label_tax(driver);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		Number number = format.parse(element.getText());
		
		return number.doubleValue();
	}
	
	public static WebElement label_totalPrice(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//tr[@class='total_price']//span[@class='pricedisplay']"));
		return element;
	}
	
	public static double get_totalPrice(WebDriver driver) throws ParseException
	{
		element = label_totalPrice(driver);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		Number number = format.parse(element.getText());
		
		return number.doubleValue();
	}
	
	public static WebElement btn_purchase(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//input[@value='Purchase']"));
		return element;
	}

}
