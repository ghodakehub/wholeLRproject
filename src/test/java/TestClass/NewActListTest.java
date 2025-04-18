package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pomclass.Login;
import Pomclass.MyNoteNewNote;
import Pomclass.NewActList;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify NewActlist")
public class NewActListTest extends NewBaseTest{
	
	@Test
	public void verifynewnote() throws InterruptedException, IOException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);

		NewActList list= new NewActList (driver);
	      list.Actlist(driver);
	       Thread.sleep(1000);
	      list.verifyNewActlsit(driver);

	
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
           	    "ActList :-NewActList",
           	    "Please check issue coming in NewActList, please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}

}
}


