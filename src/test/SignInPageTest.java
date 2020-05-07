package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.ExcelUtils;
import webpage_objects.SignInPage;

public class SignInPageTest {
	static WebDriver wd;
	static SoftAssert sa;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		sa = new SoftAssert();
		wd.get(SignInPage.SIGN_IN_URL);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@AfterClass
	public void closeDriver() {
		wd.close();
	}

	@Test
	public void logInMultipleUsers() {
		ExcelUtils.setExcel("users.xlsx");
		ExcelUtils.setWorkSheet(0);
		sa = new SoftAssert();

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			wd.navigate().to(SignInPage.SIGN_IN_URL);

			SignInPage.logIn(wd, ExcelUtils.getDataAt(i, 3), ExcelUtils.getDataAt(i, 4));

			String textBtn = SignInPage.checkSignOutBTN(wd);
			String expectedBtnText = "Sign out";
			String firstLastName = SignInPage.checkAccountName(wd);
			String expectedName = ExcelUtils.getDataAt(i, 1) + " " + ExcelUtils.getDataAt(i, 2);

			sa.assertEquals(textBtn, expectedBtnText);
			sa.assertEquals(firstLastName, expectedName);

			SignInPage.logOut(wd);
		}
		sa.assertAll();
	}
}
