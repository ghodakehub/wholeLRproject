package Pomclass;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import generic.Library;
import generic.SwitchWindow;

public class ThirdPageSocialMedialLinks extends BasePage1 {

	public ThirdPageSocialMedialLinks(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//input[@id='search']") // Main Search Box
	private WebElement searchbox;

	@FindBy(xpath = "//div[@class='search-btn']") // Main Search Icon (Button)
	private WebElement SearchButton;

	@FindBy(xpath = "//*[@id=\"select-dropdown\"]/div/div/ul/li[1]/div/div/label/span") // All
	private WebElement Allmenu;

	@FindBy(xpath = "//*[@id=\"0\"]/div/div/a/u") // first link
	private WebElement firstlink;

	@FindBy(xpath = "//*[@id=\"share\"]/li[5]/button/img") // email
	private WebElement email;

	@FindBy(xpath = "//*[@id=\"sharejudgment_user_email\"]") // email box for judgment share
	private WebElement emailbox;

	@FindBy(xpath = "//*[@id=\"share_judgment_btn\"]") // share button for judgment share
	private WebElement share;

	@FindBy(xpath = "//*[@id=\"share\"]/li[1]/button") // what's app share button for judgment share
	private WebElement whatsapp;

	@FindBy(xpath = "//*[@id=\"main_block\"]/div[1]/h2") // what's app page for judgment share
	private WebElement whatsapppage;

	@FindBy(xpath = "//*[@id=\"share\"]/li[2]/button/img") // FB share button for judgment share
	private WebElement fb;

	@FindBy(xpath = "//*[@id=\"login_form\"]/table/tbody/tr[1]/td[1]/label") // FB app page for judgment share
	private WebElement FBApp;

	@FindBy(xpath = "//*[@id=\"share\"]/li[3]/button/img") // twitter share button for judgment share
	private WebElement twitter;

	@FindBy(xpath = "//*[@id=\"layers\"]/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/div/span")
	private WebElement twitterpage;

	@FindBy(xpath = "//*[@id=\"share\"]/li[4]/button/img") // linkdin share button for judgment share
	private WebElement linkdin;

	@FindBy(xpath = "//*[@id=\"app__container\"]/main/div[3]")
	private WebElement linkdinpage;
	
	@FindBy(xpath = "/html/body/div[1]/main/div[20]/div/div/div[2]/div[1]/p")
	private WebElement Jsharetext;

	
	public void judgementShare(WebDriver driver) throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Step 1: Search and Open First Judgment
	    Library.sendKeys(driver, searchbox, "enter text is search bar", "ashiana");
	    Thread.sleep(3000);
	    Library.click(driver, SearchButton, "click on searchbtn");
	    Thread.sleep(3000);

	    firstlink.click();
	    Thread.sleep(3000);
	    SwitchWindow.switchWindowByIndex(driver, 2);
	    String caseWindow = driver.getWindowHandle(); // Save the judgment tab

	    // Step 2: Share via Email
	    email.click();
	    Thread.sleep(2000);
	    Library.waitForVisible(driver, emailbox, 5).sendKeys("pratiksha.damodar@legitquest.com");
	    share.click();

	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept();
	        Reporter.log("Email share alert handled successfully", true);
	    } catch (Exception e) {
	        System.out.println("No alert present after sharing via email.");
	    }

	    // Step 3: Share via WhatsApp, Facebook, Twitter, LinkedIn
	    shareAllPlatforms(driver, caseWindow);
	}
	private void shareAllPlatforms(WebDriver driver, String caseWindow) throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // Expected texts for each platform
	    String[] expectedTexts = {
	        "Send the following on WhatsApp", // WhatsApp
	        "Email or phone",                // Facebook
	        "Want to log in first?",         // Twitter
	        "New to LinkedIn? Join now"      // LinkedIn
	    };

	    // Platform names (for logs)
	    String[] platformNames = { "WhatsApp", "Facebook", "Twitter", "LinkedIn" };

	    for (int i = 0; i < 4; i++) { // Skip index 4 (email icon)
	        driver.switchTo().window(caseWindow);

	        // Re-fetch the icon list each time (to avoid stale elements)
	        List<WebElement> icons = driver.findElements(By.xpath("//*[@id=\"share\"]/li/button/img"));
	        WebElement icon = icons.get(i); // 0 = WhatsApp, 1 = Facebook, etc.

	        Set<String> windowsBefore = driver.getWindowHandles();
	        icon.click();

	        // Wait for new window/popup
	        wait.until(driver1 -> driver1.getWindowHandles().size() > windowsBefore.size());

	        Set<String> windowsAfter = driver.getWindowHandles();
	        windowsAfter.removeAll(windowsBefore); // get the new popup only
	        String popupWindow = windowsAfter.iterator().next();

	        driver.switchTo().window(popupWindow);

	        String actualText = "";
	        switch (i) {
	            case 0:
	                actualText = wait.until(ExpectedConditions.visibilityOf(whatsapppage)).getText(); break;
	            case 1:
	                actualText = wait.until(ExpectedConditions.visibilityOf(FBApp)).getText(); break;
	            case 2:
	                actualText = wait.until(ExpectedConditions.visibilityOf(twitterpage)).getText(); break;
	            case 3:
	                actualText = wait.until(ExpectedConditions.visibilityOf(linkdinpage)).getText(); break;
	        }

	        Assert.assertTrue(actualText.contains(expectedTexts[i]), platformNames[i] + " share failed.");
	        Reporter.log(platformNames[i] + " page opened and verified", true);

	        driver.close();
	        driver.switchTo().window(caseWindow); // Go back to case tab
	    }
	}
}
