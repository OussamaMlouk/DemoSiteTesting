package testPackage;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;

import DemoSitePage.DemoSiteLoginPage;
import DemoSitePage.DemoSiteUserPage;

@RunWith(Parameterized.class)
public class ExcelParameterizationTest {

	@Parameters
	public static Collection<Object[]> data() throws IOException {
		FileInputStream file = new FileInputStream("C:\\Users\\Admin\\Desktop\\DemoSiteDDT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows()][4];

		for (int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
			for (int colNum = 0; colNum < 3; colNum++) {
				ob[rowNum][colNum] = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			}
			ob[rowNum][3] = rowNum;
		}
		return Arrays.asList(ob);
	}

	private String username;
	private String password;
	private String expected;
	private int row;

	public ExcelParameterizationTest(String username, String password, String expected, int row) {
		super();
		this.username = username;
		this.password = password;
		this.expected = expected;
		this.row = row;
	}

	WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("phantomjs.binary.path", "C:\\Users\\Admin\\Desktop\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		driver = new PhantomJSDriver();
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void login() throws IOException {
		driver.manage().window().maximize();
		driver.get("http://thedemosite.co.uk/addauser.php");
		DemoSiteUserPage userPage = PageFactory.initElements(driver, DemoSiteUserPage.class);
		userPage.inputUser(username);
		userPage.inputPassword(password);
		userPage.save();
		driver.get("http://thedemosite.co.uk/login.php");
		DemoSiteLoginPage loginPage = PageFactory.initElements(driver, DemoSiteLoginPage.class);
		loginPage.inputUser(username);
		loginPage.inputPassword(password);
		loginPage.login();
		WebElement confirmLogin = driver
				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));

		FileInputStream file = new FileInputStream("C:\\Users\\Admin\\Desktop\\DemoSiteDDT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow rowNum = sheet.getRow(row);
		XSSFCell cell3;
		cell3 = rowNum.getCell(3);
		if (cell3 == null) {
			cell3 = rowNum.createCell(3);
		}
		cell3.setCellValue(confirmLogin.getText());
		try {
			assertEquals("Login failed", expected, confirmLogin.getText());
			cell3 = rowNum.getCell(4);
			if (cell3 == null) {
				cell3 = rowNum.createCell(4);
			}
			cell3.setCellValue("passed");
		} catch (AssertionError e) {
			cell3 = rowNum.getCell(4);
			if (cell3 == null) {
				cell3 = rowNum.createCell(4);
			}
			cell3.setCellValue("failed");
			Assert.fail();
		} finally {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Admin\\Desktop\\DemoSiteDDT.xlsx");
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

			workbook.close();
			file.close();
		} 
	}
}
