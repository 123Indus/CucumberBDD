package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerPage {
	
	WebDriver ldriver;
	
	public int TimeoutValue = 30;
	
    public CustomerPage(WebDriver rdriver) {
    	ldriver=rdriver;
    	PageFactory.initElements(new AjaxElementLocatorFactory(rdriver, TimeoutValue),this);
    	//PageFactory.initElements(rdriver,this);
    	
    	}
    
    //Find element on the web page
    
    @FindBy(xpath="(//a[contains(@class,'nav-link')])[23]")
    private WebElement lnkCustomers_menu;
    
    @FindBy(xpath="//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
    private WebElement lnkCustomers_menuitem;
    
    @FindBy(xpath="//a[contains(@class,'btn btn-primary')]/i")
    private WebElement btnAddnew;
    
    @FindBy(id="Email")
    private WebElement txtEmail;
    

	@FindBy(xpath = "//input[@id='Password']")
	private WebElement txtPassword;

	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
	private WebElement txtCustomerRoles;


	@FindBy(xpath = "//li[contains(text(),'Administrators')]")
	private WebElement listItemAdministrators;


	@FindBy(xpath = "//li[contains(text(),'Registered')]")
	private WebElement listItemRegistered;

	@FindBy(xpath = "//li[contains(text(),'Guests')]")
	private WebElement listItemGuests;


	@FindBy(xpath = "//li[contains(text(),'Guests')]")
	private WebElement listItemVendors;
	//VendorId

	@FindBy(xpath = "//*[@id='VendorId']")
	private WebElement dropdownVendorMgr;

	@FindBy(id = "Gender_Male")
	private WebElement MaleGender;


	@FindBy(id = "Gender_Female")
	private WebElement FeMaleGender;


	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement txtFirstName;


	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement txtLastName;

	@FindBy(xpath = "//input[@id='DateOfBirth']")
	private WebElement txtDob;

	@FindBy(xpath = "//input[@id='Company']")
	private WebElement txtCompanyName;

	@FindBy(xpath = "//textarea[@id='AdminComment']")
	private WebElement txtAdminContent;

	@FindBy(xpath = "//button[@name='save']")
	private WebElement btnSave;
	
	
	
	//Action method for web element 
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
		
	}
	
	public void ClickOnCustomersMenu()
	{
		lnkCustomers_menu.click();
		
	}
	
	public void clickOnCustomersMenuItem() {
		lnkCustomers_menuitem.click();
	}
	
	public void clickOnAddnew() {
		btnAddnew.click();
	}
	
	public void enterEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void enterPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void enterFirstName(String firstName)
	{
		txtFirstName.sendKeys(firstName);
	}

	public void enterLastName(String lastName)
	{
		txtLastName.sendKeys(lastName);
	}

	public void enterDob(String dob)
	{
		txtDob.sendKeys(dob);
	}

	public void enterCompanyName(String coName)
	{
		txtCompanyName.sendKeys(coName);
	}

	public void enterAdminContent(String content)
	{
		txtAdminContent.sendKeys(content);
	}
	

	/*public void enterCustomerRoles(String role)  
	{
	}*/
	

	public void enterManagerOfVendor(String value)
	{
		Select drp=new Select(dropdownVendorMgr);
		drp.selectByVisibleText(value);
		
		
	}
	
	public void enterGender(String gender)
	{
		if(gender.equals("Male"))
		{
			MaleGender.click();
		}
		else if(gender.equals("Female"))
		{
			FeMaleGender.click();
		}
		else//default set Male gender
		{
			MaleGender.click();
		}

	}

	

	public void clickOnSave()
	{
		btnSave.click();
	}

	
	
	
	
	
	
	
	
	
	

}
