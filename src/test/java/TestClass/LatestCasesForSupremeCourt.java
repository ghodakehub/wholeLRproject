package TestClass;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.LatestSupremeCourtCases;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import generic.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Latest cases")
@Feature("Verify the latest cases of Supreme court")
public class LatestCasesForSupremeCourt extends NewBaseTest{
	
	
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Description(
		    "Validate the Supreme Court latest cases :\n" +
		    "- Apply the date filter for the past 60 days.\n" +
		    "- Iterate through each listed case.\n" +
		    "- Open each case in a new tab and check if it lands on a valid judgment page.\n" +
		    "- Detect and report 404 or error pages if found, capturing screenshots for failed cases."
		)
	public void verifyLatestCases() throws Exception
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		 LatestSupremeCourtCases cases= new LatestSupremeCourtCases (driver);
		// cases.checkSupremeCourtCases(driver);
		 List<String> brokenLinks = cases.checkSupremeCourtCases(driver);

		    if (!brokenLinks.isEmpty()) {
		    	generic.AllureListeners.captureScreenshot(driver, "supremecourt error");
		    	 String screenshot=  UtilityClass.Capaturescreenshot(driver ,"supremecourtcases error" );
	             	
         		String testUrl = driver.getCurrentUrl();  
         		 ForMultiplemailReceipent.sendEmail(
                    	   driver, new String[]{"ghodake6896@gmail.com"},
                    	    "Latest cases- Supreme courtcases",
                    	    "Please check Issue of broken pages detected while checkig Supreme court cases  , please find the attached screenshot for details." ,
                    	 screenshot , testUrl
                    	   
                    	);
	
		         } else {
		             System.out.println("All cases open successfully");
		         }
	
	}
	
	
	
}



