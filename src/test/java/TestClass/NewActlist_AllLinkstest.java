package TestClass;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.annotations.Test;

import Pomclass.Login;
import Pomclass.NewActList_AllLinks;
import Pomclass.StateActListpage;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import generic.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify NewActlist")
public class NewActlist_AllLinkstest extends NewBaseTest {


	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Description(
		    "Verify 'NewActlist'functionality under 'ActList':\n" +
		    "- Ensure user can all acts links are open correctly" 
		    		)
	public void Actlistverify() throws InterruptedException, IOException, MessagingException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);

		
		 NewActList_AllLinks state= new NewActList_AllLinks(driver);
		 
		 List<String> brokenLinks = state.checkAllActLinks();

		    if (!brokenLinks.isEmpty()) {
		    	generic.AllureListeners.captureScreenshot(driver, "Newactlinks error");
		    	 String screenshot=  UtilityClass.Capaturescreenshot(driver ,"supremecourtcases error" );
	             	
	         		String testUrl = driver.getCurrentUrl();  
	         		 ForMultiplemailReceipent.sendEmail(
	                    	   driver, new String[]{"ghodake6896@gmail.com"},
	                    	    "Actlist- Newactlist",
	                    	    "Please check Issue of broken links detected while checkig NewActList links , please find the attached screenshot for details." ,
	                    	 screenshot , testUrl
	                    	   
	                    	);
		
		         } else {
		             System.out.println("All links open successfully");
		         }
	
	}
}



