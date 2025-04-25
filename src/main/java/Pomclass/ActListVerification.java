package Pomclass;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.mail.MessagingException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import UtilityClass.UtilityClass;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;
import generic.UtilityMethods;


public class ActListVerification extends BasePage1 {

	public ActListVerification(WebDriver driver) {
		super(driver);
	}

	public void clickonact() throws InterruptedException {
		WebElement actListTab = driver.findElement(By.xpath("/html/body/div[3]/main/a[2]/button"));
        actListTab.click();
        Thread.sleep(2000);
	
	}
	public void verifyDownloadButtons(String tabXpath, String tabName) throws MessagingException {
		  
		 try {
		        driver.findElement(By.xpath(tabXpath)).click();
		        Thread.sleep(3000); // Consider replacing this with explicit wait

		        String downloadPath = "C:\\Users\\Super\\Downloads";
		        int retryCount = 3; // Retry up to 3 times on 500 error

		        for (int i = 0; i < 2; i++) {
		            try {
		                // Re-fetch buttons to avoid stale element
		                List<WebElement> downloadButtons = driver.findElements(By.xpath("//a[contains(text(),'Download')]"));

		                if (i >= downloadButtons.size()) {
		                    System.out.println("⚠️ Not enough buttons on tab: " + tabName);
		                    break;
		                }

		                WebElement button = downloadButtons.get(i);

		                // Scroll to button
		                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
		                Thread.sleep(1000);

		                // Hide interfering iframes (e.g., chat widgets)
		                ((JavascriptExecutor) driver).executeScript(
		                    "let frames = document.getElementsByTagName('iframe'); " +
		                    "for(let f of frames){ f.style.display='none'; }"
		                );
		                Thread.sleep(500);

		                clearDownloadFolder(downloadPath); // Clear old files

		                boolean downloadSuccess = false;

		           
		                for (int attempt = 1; attempt <= retryCount; attempt++) {
		                    try {
		                        // Click using JS (avoid intercept issues)
		                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
		                        Thread.sleep(10000); // Wait for download to complete

		                        // Wait for the file to appear in the download directory
		                        boolean fileDownloaded = UtilityMethods.waitForFileDownload(downloadPath, ".pdf", 30); // Wait for 30 seconds
		                        if (fileDownloaded) {
		                            System.out.println(" Download successful for button " + (i + 1) + " on tab: " + tabName);
		                            downloadSuccess = true;
		                            break;
		                        } else {
		                            throw new Exception("No PDF downloaded.");
		                        }
		                    } catch (Exception e) {
		                        if (attempt == retryCount) {
		                            String failureMessage = " Download failed after " + retryCount + " attempts at index " + (i + 1) + " on tab: " + tabName;
		                           // handleDownloadFailure(tabName, i + 1, failureMessage);
		                        } else {
		                            System.out.println("Retrying download for button " + (i + 1) + " on tab: " + tabName);
		                        }
		                    }
		                }

		                if (!downloadSuccess) {
		             
		                    String pageSource = driver.getPageSource().toLowerCase();
		                    if (pageSource.contains("500") || pageSource.contains("page isn’t working") || pageSource.contains("error")) {
		                    	System.out.println ("500 error or broken page detected.");
		                  
		                        driver.navigate().back();
		                        Thread.sleep(3000); 

		                        
		                        WebElement newActsListTab = driver.findElement(By.xpath("//*[@id=\"court-tabs\"]/li[3]/a"));
		                        newActsListTab.click();
		                        Thread.sleep(3000); 
		                        
		                        return; 
		                    }
		                }

		            } catch (StaleElementReferenceException se) {
		                String reason = "stale element not found";
		                handleDownloadFailure(tabName, i + 1, reason);
		            } catch (Exception e) {
		               // handleDownloadFailure(tabName, i + 1, e.getMessage());
		            }
		        }

		    } catch (Exception e) {
		        System.out.println("Error loading tab: " + tabName);
		    }
		}

	private void handleDownloadFailure(String tabName, int buttonIndex, String reason) throws MessagingException, IOException {
	    String failureMessage = " Download failed at index " + buttonIndex + " on tab: " + tabName + "\nReason: " + reason;
	    System.out.println(failureMessage);

String screenshot=  UtilityClass.Capaturescreenshot(driver ,"Error ondonwlaod btn" );
	
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    " Actlist -downlaodButton",
           	    "Please check Actlist issue coming  on tab: " + tabName +" while downlaod pdf , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	
	public void clearDownloadFolder(String folderPath) {
	    File folder = new File(folderPath);
	    for (File file : Objects.requireNonNull(folder.listFiles())) {
	        file.delete();
	    }
	}
}

