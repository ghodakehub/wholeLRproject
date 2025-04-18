package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Pomclass.ActListForCentral;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;

public class ActlistForCentrallist extends NewBaseTest{

	
	

	
	
	@Test
	public void verifyActListforCentral() throws InterruptedException, IOException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
        ActListForCentral act= new  ActListForCentral (driver);
		act.Selecttypeforcentaral(driver);
		Thread.sleep(1000);
         act.handlebuttons(driver);
	
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
           	    "Please check Actlist for central issue coming while downlaod pdf , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	

	}
}
