package TestClass;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import org.testng.annotations.Test;
import Pomclass.Login;
import Pomclass.StateActListpage;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify stateActlist")
public class StateActList extends NewBaseTest {


	
	@Test
	@Description("This test case Verify that apply filter for type and state and check all Links and paginations")
	public void Actlistverify() throws InterruptedException, IOException, MessagingException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);

		
		 StateActListpage state= new StateActListpage(driver);
		 
		 List<String> brokenLinks = state.checkAllActLinks();

		    if (!brokenLinks.isEmpty()) {
		             String subject = "StateActList";
		             String message = "Please check 404 Broken Act Links Found on StateActList";
		             EmailUtility.sendSummaryEmailWithScreenshots(driver, subject, message, generic.Library.errorUrls, generic.Library.screenshotBytesList);
		         } else {
		             System.out.println("All tabs open successfully");
		         }
	
	}
}

