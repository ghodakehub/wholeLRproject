package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.Login;
import Pomclass.MyBookmarks;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Profile - My BookMarks")
@Feature("Verify My BookMarks Option")
public class Mybookmarkfunctionlity extends NewBaseTest{

	
	@Test
	@Description(
		    "Verify functionality of 'My Bookmarks' section:\n" +
		    "- Ensure all bookmarked judgments are listed correctly by party name.\n" +
		    "- Click on each party name to ensure the judgment page opens without error (404/500)"
		   
		)
	public void verifybookmarks() throws InterruptedException, IOException
	
	{
		 Login log= new Login(driver);
			
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		MyBookmarks marks= new MyBookmarks (driver);
	marks.validateBookmarks(driver);	
	
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
           	    "My BookMark",
           	    "Please check issue coming in My BookMark, please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	
}
}
