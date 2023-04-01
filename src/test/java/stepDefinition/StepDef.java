package stepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.CustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;
import utilities.ReadConfig;

/* child class of Base Class*/
public class StepDef extends BaseClass {
	
	//It execute before each and every scenario
	@Before("@sanity")
	public void setup()
	{
		/*readConfig=new Properties();
		try {
			
		FileInputStream file=new FileInputStream("config.properties");
		readConfig.load(file);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		
		//initiallization
		readConfig=new ReadConfig();
		System.out.println("Setup-sanity method executed");
		//String browser=readConfig.getProperty("browser");
		 String browser=readConfig.getBrowser();
		 //launch browser
		 
		 switch(browser.toLowerCase())
		 {
		 case "chrome":
			 WebDriverManager.chromedriver().setup();
			 ChromeOptions options = new ChromeOptions();
			 options.addArguments("--remote-allow-origins=*");
			 driver=new ChromeDriver(options);
			 break;
				
		 case "msedge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				driver = null;
				break;
		}
		 
		 
		 
		//WebDriverManager.chromedriver().setup();
		//driver=new ChromeDriver();
		driver.manage().window().maximize();
		//initialize logger
		Log=LogManager.getLogger("StepDef");
		Log.info("setup executed....");
	}
	
	@Before("@regression")
	public void setup1()
	{
		System.out.println("Setup1-regression method executed");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("Setup1 executed....");
	}
	
	
	
	//It execute after each and every scenario
	@After
	public void teardown(Scenario sc)
	{
		System.out.println("Tear down method executed");
		if(sc.isFailed()==true)
		{
			//Convert WebDriver Object to Screenshot
			String fileWithpath="G:\\My Drive\\EclpsePrjct1\\CucumberWithCodeStudio\\Screenshot\\test1.png";
			TakesScreenshot scrshot=(TakesScreenshot)driver;
			
			//call getScreenshotAs method to create image file
			File srcfile=scrshot.getScreenshotAs(OutputType.FILE);
			
			//Move image file to new destination
			File DestFile=new File(fileWithpath);
			
			//copy file at destination
			try {
				FileUtils.copyFile(srcfile, DestFile);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
		
		driver.quit();
	}
	
	//For @After , it works in reverse
	//If we give order=2>order=1, order2 is executed first
	/*@After(order=2)
	public void teardown1()
	{
		System.out.println("Tear down1 method executed");
		driver.quit();
	}*/
	
	//It execute before each and every step
	@BeforeStep
	public void beforeStepMethod()
	{
		System.out.println("This is before step method");
		
	}
	
	//It execute after each and every step
	/*@AfterStep
	public void afterStepMethod()
	{
		System.out.println("This is after step method");
	}*/
	
	//For taking screenshot of Failed test cases , we have to add a method
	//in @AfterStep section
	
	@AfterStep
	public void addScreenshot(Scenario scenario)
	{
		//*******If we need only failed test step screenshot, then we use if/else
		
		if(scenario.isFailed()) {
			final byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			//attach image file report(data,media type , name of the attachment)
			scenario.attach(screenshot,"image.png",scenario.getName());
		}
		
		//******If we need all step screenshot , then if/else condition not needed
		
		/*final byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		//attach image file report(data,media type , name of the attachment)
		scenario.attach(screenshot,"image.png",scenario.getName());*/
		
	}

	
   @Given("User launch chrome browser")
	public void user_launch_chrome_browser() {
        
	    lp=new LoginPage(driver);
		cp=new CustomerPage(driver);
		scp=new SearchCustomerPage(driver);
		Log.info("User launch chrome browser");
		



	}

	@When("user opens URL {string}")
	public void user_opens_url(String url) {

		driver.get(url);
		Log.info("Url Open....");


	}

	@When("User enters email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailaddress, String password) {
		lp.enterEmail(emailaddress);
		lp.enterPassword(password);
		Log.info("User enters email and password");

	}

	@When("click on login")
	public void click_on_login() {
		lp.clickloginbutton();
		Log.info("User click on Login....");
	}


	//////////////////////////Login Feature///////////////////////
	@Then("page title should be {string}")
	public void page_title_should_be(String expectedttl) {
		String actualttl=driver.getTitle();
		if(actualttl.equals(expectedttl)) {
			Log.warn("Test Passed: Login feature page title matched");
			Assert.assertTrue(true);//pass //try to import 'junit.assert' , not 'junit.framework.assert'
		}
		else
		{
			Log.warn("Test failed: Login feature page title not matched");
			Assert.assertTrue(false);//fail
		}
	}

	@When("user click on logout link")
	public void user_click_on_logout_link() {
		lp.clickLogout();
		Log.info("User Logged out....");

	}
	
	//Shift it to ClassDef.java
	/*@Then("close browser")
	public void close_browser() {

		driver.close();
		Log.info("User close the browser...");
	}*/

	

	////////////////////Add new Customer//////////////////////


	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {


		String actualttl= cp.getPageTitle();
		String expectedttl="Dashboard / nopCommerce administration";
		if(actualttl.equals(expectedttl)) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false); 
		}

	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {

		cp.ClickOnCustomersMenu();


	}

	@When("Click on Customers Menu item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		cp.clickOnCustomersMenuItem();
	}

	@When("Click on Add new button")
	public void click_on_add_new_button() {
		cp.clickOnAddnew();
	}

	@Then("User can view add new customer page")
	public void user_can_view_add_new_customer_page() {

		String expctdttl="Add a new customer / nopCommerce administration";
		String actualttl=cp.getPageTitle();

		if(actualttl.equals(expctdttl)) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}


	}

	@When("user enters customer info")
	public void user_enters_customer_info() {
		//cp.enterEmail("test1@gmail.com");
		//it generate random email id which was written in BaseClass
		cp.enterEmail(generateEmailId()+ "@gmail.com");
		cp.enterPassword("test1");
		cp.enterFirstName("Subir");
		cp.enterLastName("Guparkarta");
		cp.enterGender("Female");
		cp.enterDob("6/13/1988");
		cp.enterCompanyName("CodeStudio");
		cp.enterAdminContent("Admin content");
		cp.enterManagerOfVendor("Vendor 1");


	}

	@When("click on save button")
	public void click_on_save_button() {

		cp.clickOnSave();
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expectedCnf) {

		String actualCnf=driver.findElement(By.tagName("Body")).getText();
		if(actualCnf.contains(expectedCnf)) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}


	}

	//////////////////Search Customer////////////////////////
	/////////////////Search by Email/////////////////////////

	@When("Enter Customer Email")
	public void enter_customer_email() {

		scp.EnterEmailAddress("victoria_victoria@nopCommerce.com");


	}

	/*@When("Click on Search Button")
	public void click_on_search_button() {
		scp.ClickSearch();
	}*/
	
	@When("Click on Search button")
	public void click_on_search_button() {
		scp.ClickSearch();
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {

		String expectedEmail="victoria_victoria@nopCommerce.com";

		// Assert.assertTrue(scp.SearchCustomerByEmail(expectedEmail));	

		if(scp.SearchCustomerByEmail(expectedEmail)==true)
		{
			Assert.assertTrue(true);
		}

		else {
			Assert.assertTrue(false);
		}


	}


	//////////////Search by name ////////////////////////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() throws InterruptedException {

		//scp.ClickcollapseBar();
		Thread.sleep(2000);
		scp.enterFirstName("victoria");


	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {

		scp.enterLastName("terces");
	}

	/*@When("Click on Search button")
	public void click_on_search_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}*/

	@When("Click on Search1 button")
	public void click_on_search1_button() {

		scp.ClickSearch();
	}



	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {

		String expectedName="Victoria Terces";
        
		//One way
		/*if(scp.SearchCustomerByName(expectedName)==true)
		{
			Assert.assertTrue(true);
		}

		else {
			Assert.assertTrue(false);
		}*/
		
		//Another way
		Assert.assertTrue(scp.SearchCustomerByName(expectedName));
	}
}
