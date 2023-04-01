package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {
	
	WebDriver ldriver;
	public int TimeoutValue = 30;
	
	public LoginPage(WebDriver rdriver) {
		ldriver=rdriver;
		
		//PageFactory.initElements(rdriver,this);
		PageFactory.initElements(new AjaxElementLocatorFactory(rdriver, TimeoutValue),this);
	}
	
	@FindBy(id="Email")
	private WebElement email;
	
	
	@FindBy(id="Password")
	private WebElement password;
	
	@FindBy(xpath="//button[contains(text(),'Log in')]")
	private WebElement loginbutton;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	private WebElement Logout;
	
	
	
	public void enterEmail(String emailadd) {
		email.clear();
		email.sendKeys(emailadd);
		}
	
	public void enterPassword(String pass) {
		password.clear();
		password.sendKeys(pass);
	}
	public void clickloginbutton() {
		loginbutton.click();
	}
	
	public void clickLogout() {
		Logout.click();
	}

}
