package webpage_objects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SummerDresses {

	public static final String SUMMER_DRESSES_URL = "http://automationpractice.com/index.php?id_category=11&controller=category";
	private static final String XPATH_FIRST_DRESS = "//li[1]//div[1]//div[2]//h5[1]//a[1]";
	private static final String CSS_SELECTOR_QUANTITY = "i.icon-plus";
	private static final String XPATH_SIZE_M = "//option[contains(text(),'M')]";
	private static final String XPATH_BLUE = "//a[@name='Blue']";
	private static final String XPATH_ADD_TO_CART = "//span[contains(text(),'Add to cart')]";
	private static final String XPATH_CHECKOUT = "//span[contains(text(),'Proceed to checkout')]";
	private static final String XPATH_ADDED_DRESS_NAME = "//td[@class='cart_description']//a[contains(text(),'Printed Summer Dress')]";
	private static final String XPATH_ADDED_DRESS_QUANTITY = "//input[@name='quantity_5_24_0_0']";
	private static final String XPATH_ADDED_DRESS_COLOR_SIZE = "//td[@class='cart_description']//a[contains(text(),'Color : Blue, Size : M')]";
	private static final String XPATH_CHECKOUT2 = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]";

	public static void clickOnFirstDress(WebDriver wd) {
		wd.findElement(By.xpath(XPATH_FIRST_DRESS)).click();
	}

	public static void addQuantity(WebDriver wd) {
		wd.findElement(By.cssSelector(CSS_SELECTOR_QUANTITY)).click();
	}

	public static void chooseSizeM(WebDriver wd) {
		wd.findElement(By.xpath(XPATH_SIZE_M)).click();
	}

	public static void chooseColorBlue(WebDriver wd) {
		wd.findElement(By.xpath(XPATH_BLUE)).click();
	}

	public static void addToCart(WebDriver wd) {
		wd.findElement(By.xpath(XPATH_ADD_TO_CART)).click();
	}

	public static void goToCart(WebDriver wd) {
		WebElement el = wd.findElement(By.xpath(XPATH_CHECKOUT));
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		el.click();
	}

	public static String dressAddedQuantity(WebDriver wd) {
		return wd.findElement(By.xpath(XPATH_ADDED_DRESS_QUANTITY)).getAttribute("value");
	}

	public static String dressAddedColorSize(WebDriver wd) {
		return wd.findElement(By.xpath(XPATH_ADDED_DRESS_COLOR_SIZE)).getText();
	}

	public static String dressAddedName(WebDriver wd) {
		return wd.findElement(By.xpath(XPATH_ADDED_DRESS_NAME)).getText();

	}

	public static String navigateToRegistrationPage(WebDriver wd) {
		WebElement el = wd.findElement(By.xpath(XPATH_CHECKOUT2));
		el.click();
		return wd.getCurrentUrl();
	}

}
