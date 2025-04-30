package TestClass;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pomclass.ActListCentralTimelineAct;
import Pomclass.Login;
import Pomclass.NewStateActList;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import generic.RetryAnalyzer;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify CentralActlist")
public class ActListCentralTimeline extends NewBaseTest {


	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Description("Verify Central Act List")
	public void Actlistverify() throws InterruptedException, IOException, MessagingException
	
	{
		 Allure.step("Login with by Valid credentails");
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		 NewStateActList stateListPage = new NewStateActList(driver);
	       
	  

	        List<String> badUrls = stateListPage.checkAllActsInStateList();

	        if (!badUrls.isEmpty()) {
	            String screenshot = UtilityClass.Capaturescreenshot(driver, "Error_on_actlist");

	            // Convert list of bad URLs to single text
	            StringBuilder urlList = new StringBuilder();
	            for (String url : badUrls) {
	                urlList.append(url).append("\n");
	            }

	            ForMultiplemailReceipent.sendEmail(
	                driver,
	                new String[]{"ghodake6896@gmail.com"},
	                "ActList For State - Error Found",
	                "Hi,\n\nPlease find below bad URLs detected:\n\n" + urlList.toString() +
	                "\n\nScreenshot is attached for your reference.",
	                screenshot,
	                "https://legitquest.com/actlist#"
	            );
	        } else {
	            System.out.println("No bad URLs found.");
	        }
	}

}