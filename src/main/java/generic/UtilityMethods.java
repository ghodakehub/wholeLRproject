package generic;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
public class UtilityMethods {
	
	public static void scrollAndClick(WebDriver driver, WebElement element, String description) throws InterruptedException {
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    Library.threadSleep(1000);
	    Library.click(driver, element, description);
	}

	public static void scrollToElement(WebDriver driver, WebElement element) {
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void switchToNewTabIfOpened(WebDriver driver, String mainWindow) throws InterruptedException {
	    Set<String> allWindows = driver.getWindowHandles();
	    if (allWindows.size() > 1) {
	        for (String window : allWindows) {
	            if (!window.equals(mainWindow)) {
	                driver.switchTo().window(window);
	                break;
	            }
	        }
	        Library.threadSleep(2000);
	    }
	}

	public static void handleAlertIfPresent(WebDriver driver) {
	    try {
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	    } catch (NoAlertPresentException e) {
	        System.out.println("No alert present.");
	    }
	}


}
