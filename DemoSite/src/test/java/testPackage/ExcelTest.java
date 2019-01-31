package testPackage;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;

import DemoSitePage.DemoSiteLoginPage;
import DemoSitePage.DemoSiteUserPage;

public class ExcelTest {
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
	public void loginTest() throws Exception {
		FileInputStream file = new FileInputStream("C:\\Users\\Admin\\Desktop\\DemoSiteDDT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		DemoSiteUserPage userPage = PageFactory.initElements(driver, DemoSiteUserPage.class);

		for (int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows() ; rowNum++) {
			driver.get("http://thedemosite.co.uk/addauser.php");
			for (int colNum = 0; colNum < sheet.getRow(rowNum).getPhysicalNumberOfCells(); colNum++) {
				XSSFCell cell = sheet.getRow(rowNum).getCell(colNum);
				String userCell = cell.getStringCellValue();
				if (colNum == 0) {
					userPage.inputUser(userCell);
				}
				if (colNum == 1) {
					userPage.inputPassword(userCell);
					userPage.save();
				}
				if (colNum == 2) {
					driver.get("http://thedemosite.co.uk/login.php");
					for (int colNum1 = 0; colNum1 < sheet.getRow(rowNum).getPhysicalNumberOfCells(); colNum1++) {
						XSSFCell cell1 = sheet.getRow(rowNum).getCell(colNum1);
						String userCell1 = cell1.getStringCellValue();
						DemoSiteLoginPage loginPage = PageFactory.initElements(driver, DemoSiteLoginPage.class);
						if (colNum1 == 0) {
							loginPage.inputUser(userCell1);
						}
						if (colNum1 == 1) {
							loginPage.inputPassword(userCell1);
							loginPage.login();
						}
					}
				}
			}
			XSSFRow row = sheet.getRow(rowNum);
			XSSFCell cell3 = row.getCell(3);
			if (cell3 == null) {
				cell3 = row.createCell(3);
			}
			WebElement confirmLogin = driver
					.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
			cell3.setCellValue(confirmLogin.getText());
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Admin\\Desktop\\DemoSiteDDT.xlsx");
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			file.close();

			XSSFCell cell2 = row.getCell(2);
			XSSFCell cell4 = row.getCell(4);
			cell4 = row.createCell(4);
			if (cell3.getStringCellValue() == cell2.getStringCellValue()) {
				cell4.setCellValue("passed");
			} else if (cell3.getStringCellValue() != cell2.getStringCellValue()) {
				cell4.setCellValue("failed");
			}
		}
	}

}
