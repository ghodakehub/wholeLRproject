package Pomclass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import generic.CommonVerification;
import generic.SwitchWindow;

public class Headerslinks extends BasePage1 {

	public Headerslinks(WebDriver driver) {
		super(driver);
	}
	
	
	public List<String> verifySelectedHeaderDropdownOptions(WebDriver driver) throws InterruptedException {
	    WebElement clickOnLatestLegalCase = driver.findElement(By.xpath("/html/body/div[3]/main/a[3]/button"));
	    clickOnLatestLegalCase.click();
	    Thread.sleep(3000);
	    SwitchWindow.switchWindowByIndex(driver, 2);
	    Thread.sleep(3000);

	    List<String> brokenUrls = new ArrayList<>();
	    Actions actions = new Actions(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Define headers and dropdown items
	    Map<String, List<String>> headerWithDropdowns = new HashMap<>();
	    headerWithDropdowns.put("Products", Arrays.asList("Legal Research Tool", "Litigation Management Tool", "Legal Due Diligence - LIBIL "));
	    headerWithDropdowns.put("More", Arrays.asList("About Legitquest", "Our Team", "Career", "Contact Us"));
	    headerWithDropdowns.put("Contact Us", Collections.emptyList());

	    for (Map.Entry<String, List<String>> entry : headerWithDropdowns.entrySet()) {
	        String headerText = entry.getKey();
	        List<String> dropdownOptions = entry.getValue();

	        try {
	            WebElement header = driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle' and normalize-space(text())='"+headerText+"']"));
	            actions.moveToElement(header).click().perform();
	            Thread.sleep(1000);

	            if (dropdownOptions.isEmpty()) {
	                // If it's a direct link, check for error and go back
	                if (CommonVerification.isErrorPage(driver)) {
	                    brokenUrls.add(driver.getCurrentUrl());
	                }
	                driver.navigate().back();
	                continue;
	            }

	            for (String dropdownText : dropdownOptions) {
	                try {
	                    // Trim spaces from dropdownText and headerText
	                    String trimmedDropdownText = dropdownText.trim();
	                    String trimmedHeaderText = headerText.trim();
	                    
	                    // Use dynamic XPath
	                    WebElement dropdownItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//li[a[normalize-space(text())='" + trimmedHeaderText + "']]//a[normalize-space(text())='" + trimmedDropdownText + "']"))
	                    );
	                    dropdownItem.click();
	                    Thread.sleep(2000);

	                    if (CommonVerification.isErrorPage(driver)) {
	                        brokenUrls.add(driver.getCurrentUrl());
	                    }

	                    driver.navigate().back();
	                    Thread.sleep(2000);

	                    // Re-find the header and dropdown after navigation
	                    header = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='nav-link dropdown-toggle' and normalize-space(text())='"+trimmedHeaderText+"']")));
	                    actions.moveToElement(header).click().perform();
	                    Thread.sleep(1000);

	                } catch (Exception e) {
	                    System.out.println("Could not click dropdown: " + dropdownText + " under header: " + headerText);
	                    e.printStackTrace();
	                }
	            }
	        } catch (Exception e) {
	           // System.out.println("An error occurred while processing the header: " + headerText);
	           // e.printStackTrace();
	        }
	}
		return brokenUrls;
	}
}
