package TestClass;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.annotations.Test;
import Pomclass.CentralActList;
import Pomclass.Login;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.NewBaseTest;
import generic.RetryAnalyzer;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify CentralActlist")
public class Centralactlisttest extends NewBaseTest {


	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Description("Verify all Type of central Links and paginations")
	public void Actlistverify() throws InterruptedException, IOException, MessagingException
	
	{
		 Allure.step("Login with by Valid credentails");
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);

		 Allure.step("Click on Central actlsit and central actlist tab");
		 CentralActList central= new CentralActList(driver);
		 central.checkAllActLinks();
		 Allure.step("Click on all links and paginations");
		 
		 List<String> brokenUrls = central.checkAllActLinks();
		 if (!brokenUrls.isEmpty()) {
			 generic.AllureListeners.captureScreenshot(driver, "Actlistpage error");
	            String[] recipients = {
	            	    "ghodake6896@gmail.com"
	            	    
	            	     
	            	    
	            	};

	            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
	            	    "CentralActlist Links",
	            	    "Please check issue coming on central actlist links , see the attached screenshot for details", 
	            	  generic. Library.errorUrls, 
	            	  generic.  Library. screenshotBytesList);
	            Assert.fail("Test Case Failed: LR changepassword page");
         } 
                else{
                	System.out.println();
                }
		 }
		           
	}
	
	
	


