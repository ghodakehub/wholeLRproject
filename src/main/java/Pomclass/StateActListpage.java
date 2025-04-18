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
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.CommonVerification;
import generic.PaginationsUtility;


public class StateActListpage extends BasePage1 {

	public StateActListpage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "(//button[@class='btn btn-warning latest-case-link btn-sm d-flex align-items-center'])[2]")
	private WebElement ClickActlistbtn;
	
	
	@FindBy(xpath = "(//button[@class='btn btn-warning latest-case-link btn-sm d-flex align-items-center'])[2]")
	private WebElement stateactlist;
	
	
	    public List<String> checkAllActLinks() throws InterruptedException {
	    	ClickActlistbtn.click();
	    	List<String> brokenUrls = new ArrayList<>();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        driver.get("https://www.legitquest.com/actlist");
		    String mainWindow = driver.getWindowHandle();
	        boolean hasNext = true;

	        while (hasNext) {
	            List<WebElement> actLinks = driver.findElements(By.cssSelector("a.act-link"));

	            for (int i = 0; i < actLinks.size(); i++) {
	                actLinks = driver.findElements(By.cssSelector("a.act-link"));
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

