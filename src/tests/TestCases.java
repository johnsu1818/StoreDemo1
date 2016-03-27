package tests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePage;
import pageObjects.IphonePage;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.MyAccountLoginPage;
import pageObjects.AccountDetailPage;

//add some comments here
public class TestCases{	
	
	private String baseUrl;
	private String whatDriver;
	private static WebDriver driver = null;
	
	@Before
	public void setUp() throws Exception {
		
		//Get variables from configuration file
		Properties prop = new Properties();
		try 
		{
			//load a properties file
			prop.load(new FileInputStream("config.properties"));

			//get the property value 
			baseUrl = prop.getProperty("baseUrl");
			whatDriver = prop.getProperty("whatDriver");		
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		
		// Create driver
		if (whatDriver.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}
		else if (whatDriver.equalsIgnoreCase("IE"))
		{
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();

		}
		else 
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		
		//Implicitly Wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/* Create an order with one item (Apple iPhone 4S 16GB SIM-Free – Black)
	 * Verify item prices on product page and on checkout page are equal; Also verify total price at checkout is correct.
	 * Verify the order being created successfully at the end.
	 */
	@Test
	public void testSubmitOrder() throws ParseException, InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		openHomePage();
		HomePage.goTo_IphonePage(driver);
		
		Double currentPrice = IphonePage.get_currentPrice(driver, "Apple iPhone 4S 16GB SIM-Free – Black");
		
		// add some comments here
		WebElement iphone = IphonePage.btn_addToCart(driver, "Apple iPhone 4S 16GB SIM-Free – Black");
		wait.until(ExpectedConditions.elementToBeClickable(iphone));
		iphone.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(IphonePage.btn_goToCheckout(driver)));
		IphonePage.btn_goToCheckout(driver).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(CartPage.btn_continue(driver)));
		CartPage.btn_continue(driver).click();
		
		CheckoutPage.txtField_emailAddress(driver).clear();
		CheckoutPage.txtField_emailAddress(driver).sendKeys("johnsu@email.com");
		CheckoutPage.check_chkbox_sameAsBillingAddress(driver);
		CheckoutPage.txtField_billingFirstName(driver).clear();
		CheckoutPage.txtField_billingFirstName(driver).sendKeys("John");
		CheckoutPage.txtField_billingLastName(driver).clear();
		CheckoutPage.txtField_billingLastName(driver).sendKeys("Su");
		CheckoutPage.txtField_billingAddress(driver).clear();
		CheckoutPage.txtField_billingAddress(driver).sendKeys("100 Main Street");
		CheckoutPage.txtField_billingCity(driver).clear();
		CheckoutPage.txtField_billingCity(driver).sendKeys("Austin");
		CheckoutPage.txtField_billingState(driver).clear();
		CheckoutPage.txtField_billingState(driver).sendKeys("Texas");
		Select selectCountry = new Select(CheckoutPage.txtField_billingCountry(driver));
		selectCountry.selectByVisibleText("USA");
		CheckoutPage.txtField_billingPhone(driver).clear();
		CheckoutPage.txtField_billingPhone(driver).sendKeys("5128886969");
		
		Double totalShipping = CheckoutPage.get_totalShipping(driver);
		Double itemCost = CheckoutPage.get_itemCost(driver);
		Double tax = CheckoutPage.get_tax(driver);
		Double totalPrice = CheckoutPage.get_totalPrice(driver);
		
		// add some comments here
		assertTrue("Prices on IphonePage and on CheckoutPage aren't equal", currentPrice.equals(itemCost));
		assertTrue("Shipping + ItemCost + Tax isn't equal to TotalPrice", totalPrice == totalShipping + itemCost + tax);
		
		CheckoutPage.btn_purchase(driver).click();
		
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		String bodyText = element.getText();
		
		// add some comments here
		assertTrue("Purchase confirmation text not found!", bodyText.contains("Thank you, your purchase is pending."));
	}
	
	// add some comments here
	@Test
	public void testUpdateAccountDetails() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		openHomePage();

		// add some comments here
		WebElement myAccountLink = HomePage.lnk_myAccount(driver);
		wait.until(ExpectedConditions.elementToBeClickable(myAccountLink));
		myAccountLink.click();

		MyAccountLoginPage.login(driver, "johnsu1818", "(YrSb@I!hnqDNNB$");
		
		// add some comments here
		WebElement accountDetailsLink = AccountDetailPage.link_accountDetails(driver);
		wait.until(ExpectedConditions.elementToBeClickable(accountDetailsLink));
		accountDetailsLink.click();
		
		AccountDetailPage.txtArea_address(driver).clear();
		String newAddress = "200 Main Street";
		AccountDetailPage.txtArea_address(driver).sendKeys(newAddress);
		
		//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '200 Main Street');", AccountDetailPage.txtField_phone(driver));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", AccountDetailPage.txtField_phone(driver));
		
		// add some comments here
		WebElement saveProfileButton = AccountDetailPage.btn_saveProfile(driver);
		wait.until(ExpectedConditions.elementToBeClickable(saveProfileButton));
		saveProfileButton.click();
		
		// add some comments here
		WebElement logoutButton = AccountDetailPage.btn_logout(driver);
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		logoutButton.click();
		// add a better wait later !!!!!!!!!!!!!!
		Thread.sleep(2000);
		
		MyAccountLoginPage.login(driver, "johnsu1818", "(YrSb@I!hnqDNNB$");
		// add a better wait later !!!!!!!!!!!!!!
		Thread.sleep(2000);

		// add some comments here
		WebElement accountDetailsLink2 = AccountDetailPage.link_accountDetails(driver);
		wait.until(ExpectedConditions.elementToBeClickable(accountDetailsLink2));
		accountDetailsLink2.click();
		// add a better wait later !!!!!!!!!!!!!!
		Thread.sleep(2000);
		String str = AccountDetailPage.txtArea_address(driver).getText();
		// add some comments here
		assertTrue("New address infor. didn't get saved.", str.equals(newAddress));
	}
	
	@Test
	public void testEmptyCartMessage() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		openHomePage();
		HomePage.goTo_IphonePage(driver);
			
		// add some comments here
		WebElement iphone = IphonePage.btn_addToCart(driver, "Apple iPhone 4S 16GB SIM-Free – Black");
		wait.until(ExpectedConditions.elementToBeClickable(iphone));
		iphone.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(IphonePage.btn_goToCheckout(driver)));
		IphonePage.btn_goToCheckout(driver).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(CartPage.btn_remove(driver)));
		CartPage.btn_remove(driver).click();
	
		// add some comments here
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		String bodyText = element.getText();
		
		// add some comments here
		assertTrue("Empty cart message not found!", bodyText.contains("Oops, there is nothing in your cart."));
	}
	
		
	@After
	public void TearDown() throws InterruptedException
	{
		driver.quit();
	}
	
	public void openHomePage() {
		driver.get(baseUrl);
	}
	
}
