package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchCustomerPage {
	WebDriver ldriver;
	
	public int TimeoutValue = 30;
	
    public SearchCustomerPage(WebDriver rdriver) {
    	ldriver=rdriver;
    	PageFactory.initElements(new AjaxElementLocatorFactory(rdriver, TimeoutValue),this);
    	//PageFactory.initElements(rdriver,this);
    	
    	}
    
    @FindBy(id="SearchEmail")
    private WebElement EmailSearch; 
    
    @FindBy(id="search-customers")
    private WebElement Search;
    
    @FindBy(xpath="(//tr/th)[2]")
    private WebElement SearchResult;
    
    //@FindBy(xpath="//tr")
    @FindBy(xpath="(//tr)[3]")
    private List<WebElement> tableRows;
    //private WebElement tableRows;
    
    @FindBy(xpath="//tr/td")
    private List<WebElement> tablecolumn;
    
    
    /////////////Search by first name & Last name/////////////////////
    
    @FindBy(xpath="//div[contains(@class,'icon-collapse')]")
    private WebElement collapseBar;
    
    @FindBy(name="SearchFirstName")
    private WebElement firstname;
    
    
    @FindBy(name="SearchLastName")
    private WebElement lastName;
    
    
    
    public void EnterEmailAddress(String em) {
    	EmailSearch.sendKeys(em);
    }
    
    public void ClickSearch()
    {
    	Search.click();	
    }
    
    public boolean SearchCustomerByEmail(String expectedemail) {
    	
    	boolean found=false;
    	
    	//total number of rows in a grid
    	
    	int totalrows=tableRows.size();
    	int totalclm=tablecolumn.size();
    	
    	for(int i=1;i<=totalrows;i++) //to iterate all the rows of the grid
    	{
    		
    	     //String actualemail = ldriver.findElement(By.xpath("(//tr/td)[2]")).toString();
    		WebElement webElementEmail = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[2]"));
    		String actualemail = webElementEmail.getText();
			System.out.println(actualemail);
    	     
    	     //if we use webelement , we can use gettext() to get the text
    	     
    		for(int j=1;j<=totalclm;j++) {
    			
    			if(actualemail.equals(expectedemail)) {
    				
    				found=true;
    			
    				
    			}
    			
    			}
    		
    	}
    	
    	
    	//total number of columns
    	
		return found;
    	
    }
    
    //for clicking on collapse icon
    public void ClickcollapseBar()
    {
    	collapseBar.click();
    }
    
    //action method to enter first name 
    public void enterFirstName(String fstname)
    {
    	firstname.sendKeys(fstname);
    }
    
    //action method to enter last name 
    public void enterLastName(String lstname)
    {
    	lastName.sendKeys(lstname);
    }
    
 public boolean SearchCustomerByName(String expectedName) {
    	
    	boolean found=false;
    	
    	//total number of rows in a grid
    	
    	int totalrows=tableRows.size();
    	int totalclm=tablecolumn.size();
    	
    	for(int i=1;i<=totalrows;i++) //to iterate all the rows of the grid
    	{
    		
    	     //String actualemail = ldriver.findElement(By.xpath("(//tr/td)[2]")).toString();
    		WebElement webElementName = ldriver.findElement(By.xpath("(//tr)[3]/td[3]"));
    		String actualemail = webElementName.getText();
			System.out.println(actualemail);
    	     
    	     //if we use webelement , we can use gettext() to get the text
    	     
    		for(int j=1;j<=totalclm;j++) {
    			
    			if(actualemail.equals(expectedName)) {
    				
    				found=true;
    				break;
    			
    				
    			}
    			
    			}
    		
    	}
    	
    	
    	//total number of columns
    	
		return found;
    	
    }
    
    

}
