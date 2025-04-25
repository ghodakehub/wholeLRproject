package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.FristPageBrokenLink;
import Pomclass.Login;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("LatestLegalNews")
@Feature("Verify broken links")
public class FirstPagebrokenLinksVerify extends NewBaseTest{
	
	
	@Test
	@Description("Verify the broken links on LatestLegal News page")
	public void verifybrokenlinks() throws InterruptedException, IOException
	
	{

		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		FristPageBrokenLink links= new FristPageBrokenLink (driver);
	     links.brokenLink();
	

	}

}
