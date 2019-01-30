package testPackage;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Text;

import DemoSitePage.DemoSiteLoginPage;
import DemoSitePage.DemoSiteUserPage;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DemoSiteTest {
	
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
	public void userTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("http://thedemosite.co.uk/addauser.php");
		DemoSiteUserPage userPage = PageFactory.initElements(driver, DemoSiteUserPage.class);
		userPage.inputUser("Ouss");
		userPage.inputPassword("password");
		userPage.save();
		driver.get("http://thedemosite.co.uk/login.php");
		DemoSiteLoginPage loginPage = PageFactory.initElements(driver, DemoSiteLoginPage.class);
		loginPage.inputUser("Ouss");
		loginPage.inputPassword("password");
		loginPage.login();
		WebElement confirmLogin = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
		assertEquals("User not found", "**Successful Login**", confirmLogin.getText());
	}
	
	

	

}
