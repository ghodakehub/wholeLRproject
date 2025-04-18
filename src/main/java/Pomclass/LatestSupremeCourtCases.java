package Pomclass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.CommonVerification;
import generic.PaginationsUtility;
import generic.SwitchWindow;

public class LatestSupremeCourtCases extends BasePage1 {

	public LatestSupremeCourtCases(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "(//button[@class='btn btn-warning latest-case-link btn-sm d-flex align-items-center'])[1]")
	private WebElement clickbtn;

	public List<String> checkSupremeCourtCases(WebDriver driver) throws Exception {
	    clickbtn.click(); 
	    Thread.sleep(2000);

	    List<String> brokenUrls = new ArrayList<>();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    
	    
        WebElement filter = driver.findElement(By.xpath("//*[@id='date_filter']"));
        new Select(filter).selectByVisibleText("60 Days");
        Thread.sleep(2000);
        
       WebElement totalcasecount = driver.findElement(By.xpath("//*[@id=\"court1\"]/small"));
       String total1= totalcasecount.getText();
       System.out.println("Totatl Cases:"+total1);
	    boolean hasNext = true;

	    while (hasNext) {
	        List<WebElement> caseElements = driver.findElements(By.xpath("//*[@id='result']/div/div/a"));
	        int total = caseElements.size();

	        for (int i = 0; i < total; i++) {
	            caseElements = driver.findElements(By.xpath("//*[@id='result']/div/div/a"));
	            WebElement element = caseElements.get(i);

	            // Save handles before click
	            Set<String> handlesBefore = driver.getWindowHandles();

	            try {
	                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	                // Wait until new tab opens
	                wait.until(d -> d.getWindowHandles().size() > handlesBefore.size());

	                
	                SwitchWindow.switchWindowByIndex(driver, 2);
	                Thread.sleep(2000);

	               
	                if (CommonVerification.isErrorPage(driver)) {
	                    brokenUrls.add(driver.getCurrentUrl());
	                }
	            } catch (Exception e) {
	                System.out.println("Error in case " + i + ": " + e.getMessage());
	            } finally {
	                
	                if (driver.getWindowHandles().size() > 1) {
	                    driver.close(); 
	                    SwitchWindow.switchWindowByIndex(driver, 1);
	                    Thread.sleep(1000);
	                }
	            }
	        }

	        hasNext = PaginationsUtility.clickNextPagination(driver);
	    }

	    return brokenUrls;
	}
}
