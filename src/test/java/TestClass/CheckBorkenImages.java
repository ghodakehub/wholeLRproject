package TestClass;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Pomclass.LatestLegalPageBrokenLink2;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("LatestLegalNews")
@Feature("Verify All LatestLegalNews cases")
public class CheckBorkenImages extends NewBaseTest {

	@Test
	@Description("Verify All LatestLegalNews cases")
	public void verifybrokenImages() throws InterruptedException, MessagingException, IOException {
		
	Login log = new Login(driver);
    log.login(ConfingData_provider.Email, ConfingData_provider.Password);

    LatestLegalPageBrokenLink2 page = new LatestLegalPageBrokenLink2(driver);
  
    page.checkAllArticleImages();
}
	
	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
		 if (ITestResult.FAILURE == result.getStatus()) {
		        Throwable cause = result.getThrowable();
		        if (cause != null && cause.getMessage() != null &&
		            cause.getMessage().contains("verification failed due to error content")) {

		String screenshot=  UtilityClass.Capaturescreenshot(driver,result.getName() );
	
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    " LatestLegal News -BrokenImages",
           	    "Please check LatestLegal News issue of broken images  , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}

}}
}

   

	


