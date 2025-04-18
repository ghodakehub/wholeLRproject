package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.LatestCasesOF_TribunalCourt;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Latest cases")
@Feature("Verify the latest cases of Tribunal court")
public class LatestcasesofTribunalcourt extends NewBaseTest{
	
	
	@Test
	@Description(
		    "Validate the Tribunal Court ON latest cases page:\n" +
		    "- Apply the date filter for the past 60 days.\n" +
		    "- Iterate through each listed case.\n" +
		    "- Open each case in a new tab and check if it lands on a valid judgment page.\n" +
		    "- Detect and report 404 or error pages if found, capturing screenshots for failed cases."
		)
	public void verifyLatestCasesofTribunal() throws InterruptedException, IOException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		 LatestCasesOF_TribunalCourt courts= new LatestCasesOF_TribunalCourt(driver);
		 courts.CheckAllcaesLinksofTribynalcourtCases(driver);
	
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
           	    "LatestCases :Tribunal court",
           	    "Please check issue coming in LatestCases of TribunalCourts , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	Reporter.log("take screenshot succesfully");
	
	
}

}
