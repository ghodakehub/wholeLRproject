package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.AllFooterOptions;
import Pomclass.Login;
import generic.AllureListeners;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.Library;
import generic.NewBaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("FooterOptions")
@Feature("Verify FooterOptions")
public class CheckAllfooterOptions extends NewBaseTest {


	
	
	 @Test
	 @Description("Verify all footer options like Our Team,Gallery,Contact Us,Careers,Terms & Conditions,We value your Privacy, Newsletter")
	 		
	    public void Actlistverify() throws InterruptedException, IOException {
		 Allure.step("Login with by Valid credentails");
	        Login log = new Login(driver);
	        log.login(ConfingData_provider.Email, ConfingData_provider.Password);

	        AllFooterOptions footer = new AllFooterOptions(driver);
	        Allure.step("Click on footer options ");
	         footer.verifyallfooterOptions();  // Store failed links/screenshots
	         if (!Library.errorUrls.isEmpty()) {
	             System.out.println("Checkall footeroptions");
	             AllureListeners.captureScreenshot(driver, "Footer Page Error");
	             Assert.fail("Test Case Failed: Footer Page contains errors.");
	         } else {
	             System.out.println("FooterOptions Page Verified Successfully!");
	         }
	     }

	     @AfterMethod
	     public void sendEmailAfterExecution() throws MessagingException {
	         if (!Library.errorUrls.isEmpty()) {
	             String message = "Please check FooterOptiosn. issue is coming while open it  , See the attached url for details.";
	             String subject = "Footer Options";

	             // Send email with failed URLs and screenshots
	             EmailUtility.sendSummaryEmailWithScreenshots(driver, subject, message, Library.errorUrls, Library.screenshotBytesList);
	         } else {
	             System.out.println("Footer options Page opened successfully, no errors found.");
	         }
	     }

	 	
	}



