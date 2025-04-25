package TestClass;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.annotations.Test;
import Pomclass.Login;
import Pomclass.StateActListpage;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;
import generic.Library;
import generic.NewBaseTest;
import generic.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify stateActlist")
public class StateActList extends NewBaseTest {


	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Description("This test case Verify that apply filter for type and state and check all Links and paginations")
	public void Actlistverify() throws InterruptedException, IOException, MessagingException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);

		
		 StateActListpage state= new StateActListpage(driver);
		 
		 List<String> brokenLinks = state.checkAllActLinks();

		    if (!brokenLinks.isEmpty()) {
		    	 generic.AllureListeners.captureScreenshot(driver, "Patrol dashboard error");
		    	 String screenshot=  UtilityClass.Capaturescreenshot(driver ,"supremecourtcases error" );
	             	
	         		String testUrl = driver.getCurrentUrl();  
	         		 ForMultiplemailReceipent.sendEmail(
	                    	   driver, new String[]{"ghodake6896@gmail.com"},
	                    	    "Actlist- statactlist",
	                    	    "Please check Issue of broken links detected while checkig StateActList links , please find the attached screenshot for details." ,
	                    	 screenshot , testUrl
	                    	   
	                    	);
		
		         } else {
		             System.out.println("All links open successfully");
		         }
	
	}
}

