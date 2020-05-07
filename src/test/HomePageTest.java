package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import webpage_objects.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class HomePageTest {

	static WebDriver wd;
	String homeurl;
	static SoftAssert sa;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		homeurl = "http://automationpractice.com/index.php";
		sa = new SoftAssert();
	}

	@AfterClass
	public void finish() {
		wd.close();
	}

	@BeforeMethod
	public static void goToHomePage() {
		HomePage.openHomePage(wd);
		wd.manage().window().maximize();
	}

	@Test(priority = 0)
	public void testOpeningHomePage() {
		String actualURL = wd.getCurrentUrl();
		String expectedURL = homeurl;
		Assert.assertEquals(actualURL, expectedURL);
	}

	@Test(priority = 1)
	public void testHoverWomen() {
		HomePage.hoverToTab(wd, "women");
		String actualURL = HomePage.categoryClick(wd, "WSummer Dresses");
		String expectedURL = "http://automationpractice.com/index.php?id_category=11&controller=category";
		sa.assertEquals(actualURL, expectedURL);
		sa.assertAll();
	}

	@Test(priority = 2)
	public void testHoverDresses() {
		HomePage.hoverToTab(wd, "dresses");
		String actualURL = HomePage.categoryClick(wd, "DSummer Dresses");
		String expectedURL = "http://automationpractice.com/index.php?id_category=11&controller=category";
		Assert.assertEquals(actualURL, expectedURL);
	}

	@Test(priority = 3)
	public void testEqualSummerDressesURL() {
		HomePage.hoverToTab(wd, "women");
		String womenURL = HomePage.categoryClick(wd, "WSummer Dresses");
		HomePage.hoverToTab(wd, "dresses");
		String dressesURL = HomePage.categoryClick(wd, "DSummer Dresses");
		Assert.assertTrue(womenURL.equals(dressesURL));
	}

}
