package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.FristPageBrokenLink;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("LatestLegalNews")
@Feature("Verify broken links")
public class FirstPagebrokenLinksVerify extends NewBaseTest{
	
	
	@Test
	@Description("Verify the broken links on LatestLegal News page")
	public void verifybrokenlinks() throws InterruptedException, IOException
	
	{

		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		FristPageBrokenLink links= new FristPageBrokenLink (driver);
	     links.brokenLink();
	
	}
	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  UtilityClass.Capaturescreenshot(driver,result.getName() );

		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "Broken Links oF Home page ",
           	    "Please check find some broken links on home page , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}

	}

}
