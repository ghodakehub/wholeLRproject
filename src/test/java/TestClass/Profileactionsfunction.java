package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;
import org.testng.annotations.Test;
import Pomclass.Login;
import Pomclass.ProfileActionsfunctionality;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.Library;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Profile")
@Feature("Verify profile page")
public class Profileactionsfunction extends NewBaseTest{
	
	@Test
	@Description(
		    "Verify the Profile page loads correctly and displays and  Search history and verify options like rename , share button ,delete, date")
		    		 
		    		    
	public void profileactionsfeatures() throws InterruptedException, IOException, MessagingException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		   ProfileActionsfunctionality profact =new ProfileActionsfunctionality(driver);
		profact.verifyProfileActions(driver);
		 if (!Library.errorUrls.isEmpty()) {
             String subject = "LR-Profile";
             String message = "Please check issue coming in profile page . when click on searchHistory button it throw an error";
             EmailUtility.sendSummaryEmailWithScreenshots(driver, subject, message, generic.Library.errorUrls, generic.Library.screenshotBytesList);
         } else {
             System.out.println("Profilepage open successfully");
         }
		
	
	}

}

