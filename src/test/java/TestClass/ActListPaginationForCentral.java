package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.ActListPaginatinAndSearchText;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;

public class ActListPaginationForCentral extends NewBaseTest {
	

	
	
	@Test
	public void verifyActlistCentralPagination() throws InterruptedException, IOException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
        ActListPaginatinAndSearchText act= new  ActListPaginatinAndSearchText (driver);
		 
		act.Selecttypeforcentaral();
		
         act.VeriFyPages(driver);
	
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
           	    "Please check Actlist for central issue coming while checking paginations and searchTextBar , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	}


}
