package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Pomclass.ActListCentralTimelineAct;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify CentralActlist")
public class ActListAmendmentTimeline extends NewBaseTest{
	
	@Test
	@Description("Verify Central Act List")
	public void Actlistverify() throws InterruptedException, IOException
	
	{
		 Allure.step("Login with by Valid credentails");
	 Login log= new Login(driver);
		
	 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
	 Allure.step("Click on ActList button");
		ActListCentralTimelineAct act =new ActListCentralTimelineAct(driver);
		Allure.step("Go to actlist page and clcik on central actlist");
		act.VerifyCentralAct(driver);
		Thread.sleep(1000);
		Allure.step("Enter keyword in searh input bar and check sections");
         act.VerifyAmendemntTimeline(driver);
	
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
           	    "ActList For Central ",
           	    "Actlist for Central checking Timeline is not working , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	}



}
