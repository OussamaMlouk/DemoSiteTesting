package testPackage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import phpTravelSite.PhpTravelSiteHomePage;

public class PhpTravelSiteTest {
WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void bookingTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("https://www.phptravels.net/");
		PhpTravelSiteHomePage homePage = PageFactory.initElements(driver, PhpTravelSiteHomePage.class);
		homePage.inputCity("London");
		homePage.inputCheckIn("01/02/2019");
		homePage.inputCheckOut("03/02/2019");
		homePage.addAdult();
		homePage.addAdult();
		homePage.addAdult();
		homePage.search();
		Thread.sleep(3000);
	}
}
