package phpTravelSite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhpTravelSiteHomePage {
		
	@FindBy(xpath = "//*[@id=\"s2id_autogen8\"]/a")
	WebElement citySearchClick;

	@FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
	WebElement citySearchBox;
	
	
	
	@FindBy(xpath = "//*[@id=\"dpd1\"]/div/input")
	WebElement checkInBox;
	
	@FindBy(xpath = "//*[@id=\"dpd2\"]/div/input")
	WebElement checkOutBox;
	
	@FindBy(xpath ="//*[@id=\"adultPlusBtn\"]")
	WebElement addAdultButton;
	
	@FindBy(xpath = "//*[@id=\"hotels\"]/form/div[5]/button")
	WebElement searchButton;
	
	public void inputCity(String input) {
		citySearchClick.click();
		citySearchBox.sendKeys(input);
	}
	
	public void inputCheckIn(String input) {
		checkInBox.click();
		checkInBox.sendKeys(input);
	}
	
	public void inputCheckOut(String input) {
		checkOutBox.sendKeys(input);
	}
	
	public void addAdult() {
		addAdultButton.click();
	}
	
	public void search() {
		searchButton.click();
	}

}
