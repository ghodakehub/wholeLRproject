package TestClass;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.Headerslinks;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;
import generic.Library;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("HeaderOptions")
@Feature("Verify Headers Options")
public class Headerslinksfeature extends NewBaseTest{

	
	@Test
	@Description("Verify that the 'Product' and 'Menu' dropdown options in the header are displayed correctly upon clicking.")
	public void VerifyHeadersLinks() throws InterruptedException, IOException, MessagingException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
        Headerslinks opt=new Headerslinks (driver);
        List<String> brokenLinks = opt.verifySelectedHeaderDropdownOptions(driver);

        if (!brokenLinks.isEmpty()) {
	             String message = "Please check Header options. issue is coming while open it  , See the attached url for details.";
	             String subject = "Headers Options";

	             // Send email with failed URLs and screenshots
	             EmailUtility.sendSummaryEmailWithScreenshots(driver, subject, message, Library.errorUrls, Library.screenshotBytesList);
	         } else {
	             System.out.println("Headers options Page opened successfully, no errors found.");
	         }
	     }
    }
	

	


