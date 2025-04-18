package Pomclass;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import generic.AllureListeners;
import generic.CommonVerification;


public class AllFooterOptions extends BasePage1 {

	public AllFooterOptions(WebDriver driver) {
		super(driver);
	}


	public void verifyallfooterOptions() {
	    
		
		 List<String> footerLinksText = Arrays.asList(
			        "Our Team", "Gallery", "Contact Us", "Careers",
			        "Terms & Conditions", "We value your Privacy", "Newsletter",
			        "FAQ", "Blog", "Download for Punjab and Haryana Judiciary",
			        "Facebook", "Twitter", "LinkedIn", "YouTube"
			    );

		 SoftAssert softAssert = new SoftAssert();

		 for (String linkText : footerLinksText) {
		     try {
		         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		         CommonVerification.scrollToFooter(driver);

		         WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
		         String originalWindow = driver.getWindowHandle();
		         Set<String> oldWindows = driver.getWindowHandles();

		         link.click();
		         Thread.sleep(2000);

		         Set<String> newWindows = driver.getWindowHandles();
		         newWindows.removeAll(oldWindows);

		         if (!newWindows.isEmpty()) {
		             String newWindow = newWindows.iterator().next();
		             driver.switchTo().window(newWindow);
		             CommonVerification.verifyNoCommonErrors(driver, linkText);
		             driver.close();
		             driver.switchTo().window(originalWindow);
		         } else {
		             wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		             CommonVerification.verifyNoCommonErrors(driver, linkText);
		             driver.navigate().back();
		         }

		     } catch (Exception e) {
		        
		         AllureListeners.captureScreenshot(driver, linkText);
		         softAssert.fail("Exception in " + linkText + ": " + e.getMessage());
		         
		     }
		 }
		 softAssert.assertAll();
	
}
}
