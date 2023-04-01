package stepDefinition;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObject.CustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;
import utilities.ReadConfig;

import org.apache.logging.log4j.*;

/*Parent Class*/
public class BaseClass {
	
	
	public static WebDriver driver;
	public LoginPage lp;
	public CustomerPage cp;
	public SearchCustomerPage scp;
	public static Logger Log;
	//public Properties readConfig;
	public ReadConfig readConfig;
	
	//generate unique email id
	
	public String generateEmailId()
	{
		return(RandomStringUtils.randomAlphabetic(5));
	}
	
	
	

}
