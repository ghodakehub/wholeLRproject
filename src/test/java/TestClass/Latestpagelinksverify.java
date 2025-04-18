package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.LatestLegalPageBrokenLink2;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Latest Legal News")
@Feature("Verify Broken link of cases")
public class Latestpagelinksverify extends NewBaseTest{
	
	@Test
	@Description("Verify 'LatestLegal News'cases check broken links")
	public void verifyLatestLegalNewsbrokenlinks() throws InterruptedException, IOException
	
	{
		 Login log = new Login(driver);
	        log.login(ConfingData_provider.Email, ConfingData_provider.Password);
		LatestLegalPageBrokenLink2 link=new LatestLegalPageBrokenLink2 (driver);
		link.checkAllArticleImages();
		
	
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
           	    "BrokenLinks On Homepage",
           	    "Please check issue in coming on home page , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	
}
}
