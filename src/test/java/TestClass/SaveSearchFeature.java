package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.Login;
import Pomclass.SaveSearchJugement;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Profile")
@Feature("Verify savedsearch button")
public class SaveSearchFeature extends NewBaseTest {
	
	

	@Test
	public void verifySearchSaveFunciton() throws InterruptedException, IOException
	
	{

		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		SaveSearchJugement search= new SaveSearchJugement (driver);
	search.verifysavesearch(driver);	
	
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
           	    "LR-Profile - SavedSearch",
           	    "Please check Profile issue coming in SavedSearch , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}

}
}
