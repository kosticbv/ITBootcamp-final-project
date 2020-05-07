package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import webpage_objects.HomePage;
import webpage_objects.SummerDresses;

public class SummerDressesTest {

	static WebDriver wd;
	static SoftAssert sa;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		sa = new SoftAssert();
	}

	@BeforeMethod
	public static void goToHomePage() {
		HomePage.openHomePage(wd);
		wd.manage().window().maximize();
	}

	@AfterClass
	public void closeDriver() {
		sa.assertAll();
		wd.close();
	}

	@Test
	public void addDressToCart() {
		HomePage.hoverToTab(wd, "women");
		HomePage.categoryClick(wd, "WSummer Dresses");
		SummerDresses.clickOnFirstDress(wd);
		SummerDresses.addQuantity(wd);
		SummerDresses.chooseSizeM(wd);
		SummerDresses.chooseColorBlue(wd);
		SummerDresses.addToCart(wd);
		SummerDresses.goToCart(wd);

		String actualQuantity = SummerDresses.dressAddedQuantity(wd);
		String expectedQuantity = "2";
		sa.assertEquals(actualQuantity, expectedQuantity);

		String actualColorSize = SummerDresses.dressAddedColorSize(wd);
		String expectedColor = "Blue";
		String expectedSize = "M";
		sa.assertTrue(actualColorSize.contains(expectedColor));
		sa.assertTrue(actualColorSize.contains(expectedSize));

		String actualName = SummerDresses.dressAddedName(wd);
		String expectedName = "Printed Summer Dress";
		sa.assertEquals(actualName, expectedName);
	}
}
