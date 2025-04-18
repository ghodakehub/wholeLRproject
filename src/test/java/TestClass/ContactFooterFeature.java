package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pomclass.Contact_Footer;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("FooterOption")
@Feature("Verify footeroptions")
public class ContactFooterFeature extends NewBaseTest {
	
	

	
	@Test
	@Description("Verify All Footer Options ")
	public void verifycontactfooter() throws InterruptedException, IOException
	
	{
		
		 Allure.step("Login with by Valid credentails");
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);

        Contact_Footer footer= new Contact_Footer(driver);
		footer.click(driver);
	
	}@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  UtilityClass.Capaturescreenshot(driver,result.getName() );
		
		
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "Contact Footer Feature ",
           	    "Please check issue coming in contact footer options , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	
}
}
