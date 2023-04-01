package stepDefinition;

import io.cucumber.java.en.Then;

public class ClassDef extends BaseClass {
	
	@Then("close browser")
	public void close_browser() {

		driver.close();
		Log.info("User close the browser...");
	}

}
