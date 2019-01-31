package DemoSitePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSiteUserPage {
	@FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")
	private WebElement userInput;
	
	@FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")
	private WebElement passwordInput;
	
	@FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")
	private WebElement saveButton;
	
	
	public void inputUser(String input) {
		userInput.sendKeys(input);
	}
	
	public void inputPassword(String input) {
		passwordInput.sendKeys(input);
	}
	
	public void save() {
		saveButton.click();
	}
	
	
	
}
