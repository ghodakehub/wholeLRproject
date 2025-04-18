package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Pomclass.ActListForState;
import Pomclass.ActListForStatePdf;
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
@Feature("Verify StateActlist")
public class ActListFeatureStatePdf extends NewBaseTest{


	
	
	@Test
	@Description("Verify StateActList Pdf Button")
	public void ActlistStatePdf() throws InterruptedException, IOException
	
	{
		
		Allure.step("Login with by Valid credentails");
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		 Allure.step("Click on Actlist button");
		ActListForStatePdf act =new ActListForStatePdf (driver);
		act.SelectType();
		Allure.step("On Actlist page open actlink and clcik on pdf button and download pdf");
		Thread.sleep(1000);
		act.SelectState();
		Thread.sleep(1000);
		act.RulesOptionfromcase(driver);
	
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
           	    " PDFdwonlaod for State Actlist",
           	    "Please check Actlist for state issue coming while download , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}

}}





