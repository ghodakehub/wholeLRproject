package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.LatestLegalNewsDateSortby;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;

public class VerifydateOfLatestLegalNews extends NewBaseTest {
	

	
	@Test
	public void verifySearchBarfunction() throws InterruptedException, IOException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
	LatestLegalNewsDateSortby legal= new LatestLegalNewsDateSortby (driver);
	legal.Verifylatestlegalnewsbydatesortby(driver);	
	
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
           	    "LR-LatetLegalNews",
           	    "Please check issue coming in LatestLegalNews when we apply filter no result found , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}

}
}
