package TestClass;

import org.testng.annotations.Test;
import Pomclass.ActListVerification;
import Pomclass.Login;
import generic.ConfingData_provider;
import generic.NewBaseTest;
import io.qameta.allure.Allure;
import generic.RetryAnalyzer;
public class Aclistverifcationtest extends NewBaseTest {

	
	
	@Test( retryAnalyzer = RetryAnalyzer.class)
	 public void testactlistbuttons() throws Exception
	 {	
		Allure.step("Login with by Valid credentails");
	 Login log= new Login(driver);
		
	 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
	 Allure.step("Click on ActList button");
	 ActListVerification actsPage =new ActListVerification(driver);
	 actsPage.clickonact();
	 actsPage.verifyDownloadButtons("//a[contains(text(),'CENTRAL LIST')]", "Central List");
	 actsPage.verifyDownloadButtons("//a[contains(text(),'STATE LIST')]", "State List");
	 actsPage.verifyDownloadButtons( "//a[contains(text(),'NEW ACTS LIST')]", "New Acts List");
	  
 }
	
 }
	
	
	 

