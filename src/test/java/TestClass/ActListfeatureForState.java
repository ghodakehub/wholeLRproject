package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pomclass.ActListCentralTimelineAct;
import Pomclass.ActListForState;
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
@Feature("Verify StatActList")
public class ActListfeatureForState extends NewBaseTest{
	
	
	
	@Test
	@Description("Verify state Act List Links")
	public void Actlistverifyforstate() throws InterruptedException, IOException
	
	{
		 Allure.step("Login with by Valid credentails");
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		 Allure.step("Click on ActList and go to actlist page");
	ActListForState act= new ActListForState(driver);
	act.typeact(driver);
	Allure.step(" on ActList clcik on stateactlist and apply Type and StateFilter");
        act.stateselect();
		 Thread.sleep(1000);
		 act.pagination(driver);
 

	}

	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
		 if (ITestResult.FAILURE == result.getStatus()) {
		        Throwable cause = result.getThrowable();
		        if (cause != null && cause.getMessage() != null &&
		            cause.getMessage().contains("verification failed due to error content")) {
		String screenshot=  UtilityClass.Capaturescreenshot(driver,result.getName() );
	
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "ActList For State ",
           	    "Please check Actlist for state issue coming for state , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
		 }
}}
