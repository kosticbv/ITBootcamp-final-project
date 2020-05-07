package webpage_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

	private static final String HOME_URL = "http://automationpractice.com/index.php";
	private static final String XPATH_WOMEN = "//a[@class='sf-with-ul'][contains(text(),'Women')]";
	private static final String XPATH_DRESSES = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[6]/ul[1]/li[2]/a[1]";
	private static final String XPATH_WSUMMER_DRESSES = "//ul[@class='submenu-container clearfix first-in-line-xs']//ul//li//a[contains(text(),'Summer Dresses')]";
	private static final String XPATH_DSUMMER_DRESSES = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[6]/ul[1]/li[2]/ul[1]/li[3]/a[1]";
	private static WebElement tab, option;

	public static void openHomePage(WebDriver wd) {
		wd.get(HOME_URL);
	}

	public static void navigateToHomePage(WebDriver wd) {
		wd.navigate().to(HOME_URL);
	}

	public static void hoverToTab(WebDriver wd, String button) {
		switch (button) {
		case "women":
			tab = wd.findElement(By.xpath(XPATH_WOMEN));
			break;
		case "dresses":
			tab = wd.findElement(By.xpath(XPATH_DRESSES));
			break;
		}
		Actions action = new Actions(wd);
		action.moveToElement(tab).build().perform();
	}

	public static String categoryClick(WebDriver wd, String category) {
		switch (category) {
		case "WSummer Dresses":
			option = wd.findElement(By.xpath(XPATH_WSUMMER_DRESSES));
			break;
		case "DSummer Dresses":
			option = wd.findElement(By.xpath(XPATH_DSUMMER_DRESSES));
			break;
		}
		Actions action = new Actions(wd);
		action.moveToElement(option).build().perform();
		option.click();
		return wd.getCurrentUrl();
	}

}
