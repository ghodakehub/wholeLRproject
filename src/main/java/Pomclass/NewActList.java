package Pomclass;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import UtilityClass.Library;
import io.qameta.allure.Allure;

public class NewActList extends BasePage1 {

	public NewActList(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "/html/body/div[3]/main/a[2]/button")
	private WebElement ClickActlistbtn;
	
	@FindBy(xpath = "//*[@id=\"court-tabs\"]/li[3]/a")
	private WebElement ClickonNewActList;
	
	@FindBy(xpath = "//*[@id=\"centralActType\"]")
	private WebElement Selecttype;
	
	
	
	
		public void Actlist(WebDriver driver) throws InterruptedException
		{
			 Library.click(driver, ClickActlistbtn, "Click on actlist button");
			 Library.threadSleep(3000);
			 //Library.click(driver, ClickonNewActList, "Click on NewActlist Tab");
		}
		
		public void verifyNewActlsit(WebDriver driver) {
			
			try {
	          
				
	            WebElement newActsListButton = driver.findElement(By.linkText("NEW ACTS LIST"));
	            newActsListButton.click();

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1"))); 

	          
	            if (driver.findElements(By.tagName("body")).get(0).getText().trim().isEmpty()) {
	                System.out.println("Page is blank. Test failed.");
	                Allure.step("");
	                System.out.println("Error URL: " + driver.getCurrentUrl());
	                Allure.step("Error Url :"+driver.getCurrentUrl());
	                
	            } else {
	                System.out.println("New Acts List page loaded successfully.");

	               
	                List<WebElement> actsList = driver.findElements(By.xpath("//*[@id=\"result\"]/tr/td[1]/a")); 

	              
	                for (int i = 0; i < Math.min(actsList.size(), 5); i++) {
	                    WebElement act = actsList.get(i);
	                    String actName = act.getText();
	                    System.out.println("Checking act: " + actName);
	                    
	                   
	                    String originalWindow = driver.getWindowHandle();
	                    

	                   
	                    act .click();

	                    Thread.sleep(2000);
	                    
	                    
	                    wait.until(new ExpectedCondition<Boolean>() {
	                        public Boolean apply(WebDriver driver) {
	                            return driver.getWindowHandles().size() > 1;
	                        }
	                    });

	                    // Switch to the new tab
	                    Set<String> windowHandles = driver.getWindowHandles();
	                    for (String handle : windowHandles) {
	                        if (!handle.equals(originalWindow)) {
	                            driver.switchTo().window(handle);
	                            break;
	                        }
	                    }
	                    
	                    
	                    
	                   
	                    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))); 

	                    
	                    if (driver.findElements(By.tagName("body")).get(0).getText().trim().isEmpty()) {
	                        System.out.println("Act page is blank: " + actName);
	                        System.out.println("Error URL: " + driver.getCurrentUrl());
	                    } else {
	                        System.out.println("Act page loaded successfully: " + actName);
	                    }

	                    // Navigate back to the acts list
	                    driver.close();
	                    driver.switchTo().window(originalWindow);
	                    
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }
	
}