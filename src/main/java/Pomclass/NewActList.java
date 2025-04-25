package Pomclass;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import generic.EmailUtility;
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
	   
	    String downloadDir = "C:\\Users\\Super\\Downloads"; // Update to your folder
	    String[] tabs = {"STATE LIST", "CENTRAL LIST", "NEW ACTS LIST"};

	    public void clicactlist() throws InterruptedException
	    {
	    	driver.findElement(By.xpath("/html/body/div[3]/main/a[2]/button")).click();
	    	Thread.sleep(1000);
	    }
	    public void validateAllTabs() throws Exception {
	        for (String tab : tabs) {
	            navigateToTab(tab);
	            validateDownloadButtons(tab);
	        }
	        
	    }

	    public void navigateToTab(String tabName) throws InterruptedException {
	        WebElement tabElement = driver.findElement(By.linkText(tabName));
	        tabElement.click();
	        Thread.sleep(2000); 
	    }

	    public void validateDownloadButtons(String tabName) throws Exception {
	        List<WebElement> downloadButtons = driver.findElements(By.xpath("//a[contains(text(),'Download')]"));

	        for (int i = 0; i < Math.min(downloadButtons.size(), 2); i++) {
	            
	            try {
	                ((JavascriptExecutor) driver).executeScript(
	                    "document.querySelectorAll('iframe[title=\"chat widget\"]')[0].style.display='none';");
	            } catch (Exception e) {
	                System.out.println("Chat widget not found or already hidden.");
	            }

	            File before = getLatestFileFromDir(downloadDir);
	            WebElement button = downloadButtons.get(i);

	         
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

	            Thread.sleep(5000); 
	           
	            String pageSource = driver.getPageSource().toLowerCase();
	            if (pageSource.contains("http error 500") || pageSource.contains("this page isn’t working")) {
	                System.out.println("Server error after clicking download on tab: " + tabName);
	                takeScreenshot(tabName + "_ServerError_" + i + ".png");
	                generic.AllureListeners.captureScreenshot(driver, "pdf error");
		            String[] recipients = {
		            	    "ghodake6896@gmail.com"
		            	    
		            	     
		            	    
		            	};

		            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
		            	    "LR -Actlist download pdf ",
		            	    "Please check issue in actlist while downlaoding pdf for tab :"+tabName+ ", see the attached screenshot for details", 
		            	  generic. Library.errorUrls, 
		            	  generic.  Library. screenshotBytesList);
	                
	                
	                continue;
	            }

	            File after = getLatestFileFromDir(downloadDir);
	            if (before == null || after == null || before.getName().equals(after.getName())) {
	                System.out.println("Download FAILED on tab: " + tabName);
	                takeScreenshot(tabName + "_DownloadFail_" + i + ".png");
	                
	            } else {
	                System.out.println("Download SUCCESSFUL on tab: " + tabName);
	            }
	        }
	    }

	    public File getLatestFileFromDir(String dirPath) {
	        File dir = new File(dirPath);
	        File[] files = dir.listFiles(File::isFile);
	        if (files == null || files.length == 0) return null;
	        return Arrays.stream(files).max(Comparator.comparingLong(File::lastModified)).orElse(null);
	    }

	    public void takeScreenshot(String fileName) throws Exception {
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(scrFile, new File("D:\\Legitquest\\Screenshots" + fileName));
	    }

			} 

