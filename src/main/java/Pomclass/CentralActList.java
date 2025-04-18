package Pomclass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.CommonVerification;
import generic.PaginationsUtility;

public class CentralActList extends BasePage1 {

	public CentralActList(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "(//button[@class='btn btn-warning latest-case-link btn-sm d-flex align-items-center'])[2]")
	private WebElement ClickActlistbtn;
	
	
	@FindBy(xpath = "//*[@id=\"court-tabs\"]/li[2]/a")
	private WebElement centralactlist;
	
	
	public List<String> checkAllActLinks() throws InterruptedException {
	    ClickActlistbtn.click(); // opens act list page
	    Thread.sleep(2000);

	    centralactlist.click();

	    new WebDriverWait(driver, Duration.ofSeconds(10)).until(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'')]"))
	    );

	    
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
	    
	    List<String> brokenUrls = new ArrayList<>();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    String mainWindow = driver.getWindowHandle();
	    boolean hasNext = true;

	    while (hasNext) {
	        List<WebElement> actLinks = driver.findElements(By.cssSelector("a.act-link"));

	        for (int i = 0; i < actLinks.size(); i++) {
	            actLinks = driver.findElements(By.cssSelector("a.act-link")); // refresh list
	            WebElement actLink = actLinks.get(i);

	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actLink);
	            Thread.sleep(1500);
	            actLink.click();

	            wait.until(driver -> driver.getWindowHandles().size() > 1);
	            Set<String> allWindows = driver.getWindowHandles();

	            for (String win : allWindows) {
	                if (!win.equals(mainWindow)) {
	                    driver.switchTo().window(win);
	                    break;
	                }
	            }

	            if (CommonVerification.isErrorPage(driver)) {
	              brokenUrls.add(driver.getCurrentUrl());
	              
	            }

	            driver.close();
	            driver.switchTo().window(mainWindow);
	            Thread.sleep(1500);
	        }

	        hasNext = PaginationsUtility.clickNextPagination(driver);
	    }

	    return brokenUrls;
	}
}

