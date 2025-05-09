package TestClass;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pomclass.Headerslinks;
import Pomclass.Login;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.Library;
import generic.NewBaseTest;
import generic.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("HeaderOptions")
@Feature("Verify Headers Options")
public class Headerslinksfeature extends NewBaseTest{

	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Description("Verify that the 'Product' and 'Menu' dropdown options in the header are displayed correctly upon clicking.")
	public void VerifyHeadersLinks() throws InterruptedException, IOException, MessagingException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
        Headerslinks opt=new Headerslinks (driver);
        List<String> brokenLinks = opt.verifySelectedHeaderDropdownOptions(driver);

        if (!brokenLinks.isEmpty()) {
        	 generic.AllureListeners.captureScreenshot(driver, "Patrol dashboard error");
	            String[] recipients = {
	            	    "ghodake6896@gmail.com", 
	            	    
	            	};

	            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
	            	    "LR - HeaderOptions",
	            	    "Please check issue coming on header options , see the attached screenshot for details", 
	            	   Library.errorUrls, 
	            	   Library. screenshotBytesList);
	            Assert.fail("Test Case headreoption");
	         } else {
	             System.out.println("Headers options Page opened successfully, no errors found.");
	         }
	     }
    }
	

	


