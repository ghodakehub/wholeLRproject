package TestClass;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.annotations.Test;

import Pomclass.Login;
import Pomclass.NewActList_AllLinks;
import Pomclass.StateActListpage;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify NewActlist")
public class NewActlist_AllLinkstest extends NewBaseTest {


	
	@Test
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
		             String subject = "NewActList";
		             String message = "Please check 404 Broken issue Act Links Found on NewActList";
		             EmailUtility.sendSummaryEmailWithScreenshots(driver, subject, message, generic.Library.errorUrls, generic.Library.screenshotBytesList);
		         } else {
		             System.out.println("All tabs open successfully");
		         }
	
	}
}



