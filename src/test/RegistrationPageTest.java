package test;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.ExcelUtils;
import webpage_objects.HomePage;
import webpage_objects.RegistrationPage;
import webpage_objects.SignInPage;
import webpage_objects.SummerDresses;

public class RegistrationPageTest {

	static WebDriver wd;
	static SoftAssert sa;
	static Scanner s;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		sa = new SoftAssert();
		wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@AfterClass
	public void closeDriver() {
		wd.close();
	}

	@Test
	public void createAccount() {
		HomePage.openHomePage(wd);
		wd.manage().window().maximize();
		HomePage.hoverToTab(wd, "women");
		HomePage.categoryClick(wd, "WSummer Dresses");
		SummerDresses.clickOnFirstDress(wd);
		SummerDresses.addQuantity(wd);
		SummerDresses.chooseSizeM(wd);
		SummerDresses.chooseColorBlue(wd);
		SummerDresses.addToCart(wd);
		SummerDresses.goToCart(wd);

		String actualURL = SummerDresses.navigateToRegistrationPage(wd);
		String expectedURL = RegistrationPage.URL;
		sa.assertEquals(actualURL, expectedURL);

		RegistrationPage.createTestEmail();

		RegistrationPage.registrationFillIn(wd, RegistrationPage.email);

		RegistrationPage.insertFirstName(wd, "Bozidar");
		RegistrationPage.insertLastName(wd, "Kostic");
		RegistrationPage.insertPassword(wd, "test123");
		RegistrationPage.insertAddress(wd, "76 West Street");
		RegistrationPage.insertCity(wd, "Houston");
		RegistrationPage.choseState(wd, "Texas");
		RegistrationPage.insertZipCode(wd, "41000");
		RegistrationPage.choseCountry(wd, "United States");
		RegistrationPage.insertPhoneNum(wd, "123456789");
		RegistrationPage.insertAlias(wd, "76WS");
		RegistrationPage.submitRegistration(wd);

		String textBtn = SignInPage.checkSignOutBTN(wd);
		String expectedBtnText = "Sign out";
		String firstLastName = SignInPage.checkAccountName(wd);
		String expectedName = "Bozidar Kostic";

		sa.assertEquals(textBtn, expectedBtnText);
		sa.assertEquals(firstLastName, expectedName);
		sa.assertAll();
		SignInPage.logOut(wd);
	}

	@Test
	public static void createMultipleAccounts(){
		wd.get(RegistrationPage.URL);
		wd.manage().window().maximize();

		ExcelUtils.setExcel("users.xlsx");
		ExcelUtils.setWorkSheet(0);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			wd.navigate().to(RegistrationPage.URL);

			RegistrationPage.registrationFillIn(wd, ExcelUtils.getDataAt(i, 3));

			RegistrationPage.insertFirstName(wd, ExcelUtils.getDataAt(i, 1));
			RegistrationPage.insertLastName(wd, ExcelUtils.getDataAt(i, 2));
			RegistrationPage.insertPassword(wd, ExcelUtils.getDataAt(i, 4));
			RegistrationPage.insertAddress(wd, ExcelUtils.getDataAt(i, 5));
			RegistrationPage.insertCity(wd, ExcelUtils.getDataAt(i, 6));
			RegistrationPage.choseState(wd, ExcelUtils.getDataAt(i, 7));
			RegistrationPage.insertZipCode(wd, ExcelUtils.getDataAt(i, 8));
			RegistrationPage.choseCountry(wd, ExcelUtils.getDataAt(i, 9));
			RegistrationPage.insertPhoneNum(wd, ExcelUtils.getDataAt(i, 10));
			RegistrationPage.insertAlias(wd, ExcelUtils.getDataAt(i, 11));
			RegistrationPage.submitRegistration(wd);

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
