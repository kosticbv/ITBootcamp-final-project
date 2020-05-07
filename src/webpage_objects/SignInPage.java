package webpage_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage {

	public static final String SIGN_IN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
	private static final String ID_EMAIL = "email";
	private static final String ID_PASSWORD = "passwd";
	private static final String SIGN_IN_BTN = "SubmitLogin";
	private static final String XPATH_LOGOUT = "//a[@class='logout']";
	private static final String XPATH_ACCOUNT = "//a[@class='account']";
	private static final String XPATH_LOGIN = "//a[@class='login']";

	public static void openSignInPage(WebDriver wd) {
		wd.get(SIGN_IN_URL);
	}

	public static void logIn(WebDriver wd, String mail, String password) {
		WebElement el = wd.findElement(By.id(ID_EMAIL));
		el.click();
		el.sendKeys(mail);
		el = wd.findElement(By.id(ID_PASSWORD));
		el.click();
		el.sendKeys(password);
		el = wd.findElement(By.name(SIGN_IN_BTN));
		el.click();
	}

	public static String checkSignOutBTN(WebDriver wd) {
		return wd.findElement(By.xpath(XPATH_LOGOUT)).getText();
	}

	public static String checkAccountName(WebDriver wd) {
		return wd.findElement(By.xpath(XPATH_ACCOUNT)).getText();
	}

	public static void logOut(WebDriver wd) {
		wd.findElement(By.xpath(XPATH_LOGOUT)).click();
	}

	public static String checkSignInBTN(WebDriver wd) {
		return wd.findElement(By.xpath(XPATH_LOGIN)).getText();
	}

}
