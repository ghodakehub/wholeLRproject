package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.Login;
import Pomclass.iDrafFeatures;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Jugement Idraf")
@Feature("Verify iDraf Options")
public class iDrafFeaturesTest extends NewBaseTest{
	
	@Test
	  @Description("Verify that the 'Issue',Fact, Reasoning , Arguement of Petitioner ,Arguement of Respondent and Decision section in IDraf is displayed and highlight the content correctly")
	public void Actlistverify() throws InterruptedException, IOException
	
	{
	 Login log= new Login(driver);
		
	 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
	 iDrafFeatures idf= new iDrafFeatures(driver);
	 idf.verifyIdrafFeatures();
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
           	    "Gallery ",
           	 "Gallery functionality encountered an error.\n\nURL: " + testUrl + "\n\nPlease find the attached screenshot.",
           	 screenshot , testUrl
           	   
           	);
	
	}
	}

}
